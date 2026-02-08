package model;

public class Author extends BaseEntity {
    private int rating;

    public Author(int id, String name, int rating) {
        super(id, name);
        setRating(rating);
    }

    @Override
    public void printInfo() {
        System.out.println("Author | ID: " + getId() + " | Name: " + getName() + " | Rating: " + rating);
    }

    @Override
    public String getEntityType() {
        return "Author";
    }

    public int getRating() { return rating; }
    public void setRating(int rating) {
        if (rating < 0 || rating > 10) throw new IllegalArgumentException("Rating must be 0-10");
        this.rating = rating;
    }
}
