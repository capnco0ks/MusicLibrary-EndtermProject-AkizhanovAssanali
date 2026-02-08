package model.interfaces;

public interface Validatable {
    boolean isValid();

    default void validate() {
        if (!isValid()) {
            throw new IllegalArgumentException("Validation failed");
        }
    }

    static boolean isNullOrEmpty(String s) {
        return s == null || s.isBlank();
    }
}
