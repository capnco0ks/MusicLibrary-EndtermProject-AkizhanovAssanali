package controller;

import dto.AuthorRequest;
import model.Author;
import service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cache.AuthorCacheSingleton;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> getAll() {
        return authorService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getById(@PathVariable int id) {
        Author author = authorService.getById(id);
        return ResponseEntity.ok(author);
    }

    @PostMapping
    public ResponseEntity<Author> create(@RequestBody AuthorRequest request) {
        Author author = new Author(request.getId(), request.getName(), request.getRating());
        authorService.create(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(author);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> update(@PathVariable int id, @RequestBody AuthorRequest request) {
        authorService.update(id, request.getName(), request.getRating());
        Author updated = authorService.getById(id);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/cache")
    public ResponseEntity<Void> clearCache() {
        authorService.clearCache();
        return ResponseEntity.noContent().build();
    }
}
