package model.interfaces;

public interface Printable {
    void printInfo();

    default void printHeader(String header) {
        System.out.println("=== " + header + " ===");
    }

    static void printFooter() {
        System.out.println("==================");
    }
}
