package cache;

import model.Author;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class AuthorCacheSingleton {
    private static final AuthorCacheSingleton INSTANCE = new AuthorCacheSingleton();
    private static final String ALL_AUTHORS_KEY = "allAuthors";

    private final Map<String, List<Author>> cache = new ConcurrentHashMap<>();

    private AuthorCacheSingleton() {
    }

    public static AuthorCacheSingleton getInstance() {
        return INSTANCE;
    }

    public List<Author> getAllAuthors() {
        return cache.get(ALL_AUTHORS_KEY);
    }

    public void putAllAuthors(List<Author> authors) {
        cache.put(ALL_AUTHORS_KEY, authors);
    }

    public void clear() {
        cache.clear();
    }
}