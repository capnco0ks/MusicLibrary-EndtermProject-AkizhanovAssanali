package exception;

public class AuthorNotFoundException extends NotFoundException {
    public AuthorNotFoundException(int id) {
        super("Author with id " + id + " not found");
    }
}
