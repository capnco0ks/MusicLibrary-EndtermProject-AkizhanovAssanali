import model.Author;
import model.Category;
import model.Media;
import patterns.AuthorBuilder;
import patterns.MediaFactory;
import repository.AuthorRepository;
import repository.interfaces.CrudRepository;
import service.AuthorService;
import utils.ReflectionUtils;
import utils.SortingUtils;
import java.util.List;
import java.util.Scanner;
import config.DatabaseConfigManager;
import config.ApplicationConfig;
import config.LoggingService;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // a
        CrudRepository<Author> authorRepo = new AuthorRepository();
        AuthorService authorService = new AuthorService(authorRepo);

        DatabaseConfigManager manager = DatabaseConfigManager.getInstance();
        try (Connection conn = manager.getConnection()) {
            System.out.println("Connected successfully");
        } catch (SQLException e) {
            throw new RuntimeException("Database connection check failed", e);
        }

        ApplicationConfig appConfig = ApplicationConfig.getInstance();
        System.out.println("App: " + appConfig.getApplicationName() + " v" + appConfig.getApplicationVersion());
        LoggingService.getInstance().info("Application started");

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("---------");
            System.out.println("DB OPERATIONS");
            System.out.println("---------");
            System.out.println("Choose action:");
            System.out.println("1 - Create Author");
            System.out.println("2 - List Authors");
            System.out.println("3 - Update Author");
            System.out.println("4 - Delete Author");
            System.out.println("5 - Inspect Author Class (Reflection)");
            System.out.println("6 - Demo Polymorphism: Media (Song/Podcast)");
            System.out.println("7 - EXIT");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter ID:");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter name:");
                    String name = sc.nextLine();
                    System.out.println("Enter rating (0-10):");
                    int rating = sc.nextInt();
                    sc.nextLine();

                    Author author = new AuthorBuilder().id(id).name(name).rating(rating).build();
                    author.validate();
                    authorService.create(author);
                    System.out.println("Author created successfully.");
                }
                case 2 -> {
                    List<Author> authors = authorService.getAll();
                    SortingUtils.sortList(authors, (a1, a2) -> a2.getRating() - a1.getRating());
                    System.out.println("=== Authors List (sorted by rating desc) ===");
                    authors.forEach(Author::printInfo);
                    System.out.println("===========================================");
                }
                case 3 -> {
                    System.out.println("Enter ID to update:");
                    int upId = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter new name:");
                    String newName = sc.nextLine();
                    System.out.println("Enter new rating (0-10):");
                    int newRating = sc.nextInt();
                    sc.nextLine();

                    authorService.update(upId, newName, newRating);
                    System.out.println("Author updated successfully.");
                }
                case 4 -> {
                    System.out.println("Enter ID to delete:");
                    int delId = sc.nextInt();
                    sc.nextLine();
                    authorService.delete(delId);
                    System.out.println("Author deleted successfully.");
                }
                case 5 -> {
                    List<Author> authors = authorService.getAll();
                    if (!authors.isEmpty()) {
                        ReflectionUtils.inspectClass(authors.get(0));
                    } else {
                        System.out.println("No authors in database to inspect.");
                    }
                }
                case 6 -> {
                    List<Author> authors = authorService.getAll();
                    if (authors.isEmpty()) {
                        System.out.println("No authors available to demo media polymorphism.");
                        break;
                    }

                    Author author1 = authors.get(0);

                    Category songCategory = new Category(1, "Song");
                    Category podcastCategory = new Category(2, "Podcast");

                    Media song = MediaFactory.createSong(101, "Lose Yourself", 320, author1, "Rap", songCategory);
                    Media podcast = MediaFactory.createPodcast(201, "VPISKA kizaru", 45, author1, 12, podcastCategory);

                    System.out.println("=== Polymorphism Demo ===");
                    Media[] mediaList = {song, podcast};
                    for (Media m : mediaList) {
                        System.out.println("Type: " + m.getEntityType());
                        m.printInfo();
                        m.play();
                    }
                    System.out.println("=========================");
                }
                case 7 -> {
                    System.out.println("Bye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }
}
