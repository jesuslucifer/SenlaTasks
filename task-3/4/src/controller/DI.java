package controller;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.HashMap;

public class DI {
    private static final Map<Class<?>, Object> instances = new HashMap<>();


    public static <T> void register(Class<T> clazz, T obj) {
        instances.put(clazz, obj);
    }

    public static void injectDependencies(Object object) {
        Class <?> clazz = object.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);
                try {
                    field.set(object, getInstance(field.getType()));
                } catch (IllegalAccessException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    private static Object getInstance(Class<?> clazz) {
        if (instances.containsKey(clazz)) {
            return instances.get(clazz);
        }

        Object instance = createInstance(clazz);
        if (instance != null) {
            instances.put(clazz, instance);
            injectDependencies(instance);
        }
        return instance;
    }

    private static Object createInstance(Class<?> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
