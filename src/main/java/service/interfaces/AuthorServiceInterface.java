package service.interfaces;

import model.Author;

import java.util.List;

public interface AuthorServiceInterface {
    void create(Author author);
    List<Author> getAll();
    Author getById(int id);
    void update(int id, String newName, int newRating);
    void delete(int id);
}
