package utils;

import java.util.Comparator;
import java.util.List;

public class SortingUtils {

    public static <T> void sortList(List<T> list, Comparator<T> comparator) {
        list.sort(comparator);
    }
}
