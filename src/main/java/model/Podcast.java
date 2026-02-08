package model;

public class Podcast extends Media {
    private int episodeNumber;

    public Podcast(int id, String name, int duration, Author author, int episodeNumber, Category category) {
        super(id, name, duration, author, category);
        this.episodeNumber = episodeNumber;
    }

    @Override
    public void printInfo() {
        System.out.println("Podcast | ID: " + getId() + " | Name: " + getName() + " | Duration: " + getDuration() +
                " | Author: " + getAuthor().getName() + " | Category: " + getCategory().getName() +
                " | Episode: " + episodeNumber);
    }

    @Override
    public String getMediaType() {
        return "Podcast";
    }

    @Override
    public void play() {
        System.out.println("Playing podcast episode: " + episodeNumber);
    }

    @Override
    public String getEntityType() {
        return "Podcast";
    }

    public int getEpisodeNumber() { return episodeNumber; }
}
