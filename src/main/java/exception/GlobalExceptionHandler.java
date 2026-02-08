package exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class, AuthorNotFoundException.class, NotFoundException.class})
    public ResponseEntity<Map<String, Object>> handleNotFound(RuntimeException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", e.getMessage(), "status", 404));
    }

    @ExceptionHandler({InvalidInputException.class, DuplicateResourceException.class})
    public ResponseEntity<Map<String, Object>> handleBadRequest(RuntimeException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", e.getMessage(), "status", 400));
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<Map<String, Object>> handleDataBase(DataBaseException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("message", e.getMessage() != null ? e.getMessage() : "Database error", "status", 500));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleOther(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("message", e.getMessage() != null ? e.getMessage() : "Internal server error", "status", 500));
    }
}
