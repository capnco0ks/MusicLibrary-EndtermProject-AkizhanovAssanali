package patterns;

import model.Author;
import model.Category;
import model.Media;
import model.Podcast;
import model.Song;

public class MediaFactory {

    public static Media createSong(int id, String name, int duration, Author author, String genre, Category category) {
        return new Song(id, name, duration, author, genre, category);
    }

    public static Media createPodcast(int id, String name, int duration, Author author, int episodeNumber, Category category) {
        return new Podcast(id, name, duration, author, episodeNumber, category);
    }

}
