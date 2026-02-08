package model;

import model.interfaces.Playable;
import model.interfaces.Validatable;

public abstract class Media extends BaseEntity implements Playable {
    private int duration;
    private Author author;
    private Category category;

    public Media(int id, String name, int duration, Author author, Category category) {
        super(id, name);
        setDuration(duration);
        this.author = author;
        this.category = category;
    }

    @Override
    public void validate() {
        super.validate();
        if (duration <= 0 || author == null || category == null) {
            throw new IllegalArgumentException("Invalid Media data");
        }
    }

    public int getDuration() { return duration; }
    public void setDuration(int duration) {
        if (duration <= 0) throw new IllegalArgumentException("Duration must be positive");
        this.duration = duration;
    }

    public Author getAuthor() { return author; }
    public Category getCategory() { return category; }

    public abstract String getMediaType();
}
