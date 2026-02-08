package model;

public class Song extends Media {
    private String genre;

    public Song(int id, String name, int duration, Author author, String genre, Category category) {
        super(id, name, duration, author, category);
        this.genre = genre;
    }

    @Override
    public void printInfo() {
        System.out.println("Song | ID: " + getId() + " | Name: " + getName() + " | Duration: " + getDuration() +
                " | Author: " + getAuthor().getName() + " | Category: " + getCategory().getName() +
                " | Genre: " + genre);
    }

    @Override
    public String getMediaType() {
        return "Song";
    }

    @Override
    public void play() {
        System.out.println("Playing song: " + getName());
    }

    @Override
    public String getEntityType() {
        return "Song";
    }

    public String getGenre() { return genre; }
}
