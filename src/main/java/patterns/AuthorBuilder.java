package patterns;

import model.Author;

public class AuthorBuilder {
    private int id;
    private String name;
    private int rating;

    public AuthorBuilder id(int id) {
        this.id = id;
        return this;
    }

    public AuthorBuilder name(String name) {
        this.name = name;
        return this;
    }

    public AuthorBuilder rating(int rating) {
        this.rating = rating;
        return this;
    }

    public Author build() {
        return new Author(id, name, rating);
    }
}
