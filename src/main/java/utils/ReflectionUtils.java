package utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtils {

    public static void inspectClass(Object obj) {
        Class<?> clazz = obj.getClass();
        System.out.println("Class Name: " + clazz.getName());

        System.out.println("Fields:");
        Class<?> current = clazz;
        while (current != null) {
            for (Field field : current.getDeclaredFields()) {
                field.setAccessible(true);
                System.out.println(" - " + field.getName() + " : " + field.getType().getSimpleName());
            }
            current = current.getSuperclass();
        }

        System.out.println("Methods:");
        current = clazz;
        while (current != null) {
            for (Method method : current.getDeclaredMethods()) {
                System.out.println(" - " + method.getName() + "()");
            }
            current = current.getSuperclass();
        }
    }
}
