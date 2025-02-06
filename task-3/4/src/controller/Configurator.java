package controller;

import model.RoomStatus;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

public class Configurator {
    public static void configure(Object object) throws Exception {
        Class<?> clazz = object.getClass();
        Properties properties = new Properties();

        try(FileInputStream fis = new FileInputStream("task-3/4/src/resources/config.property")) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(Field field : clazz.getDeclaredFields()) {
            if(field.isAnnotationPresent(ConfigProperty.class)) {
                String propertyName = field.getAnnotation(ConfigProperty.class).propertyName();

                if(propertyName.isEmpty()) {
                    propertyName = clazz.getSimpleName() + "." + field.getName();
                }

                String value = properties.getProperty(propertyName);

                field.setAccessible(true);
                if(value != null) {
                    if(field.getType() == Integer.class || field.getType() == int.class) {
                        field.set(object, Integer.parseInt(value));
                    } else  if (field.getType() == Boolean.class || field.getType() == boolean.class) {
                        field.set(object, Boolean.parseBoolean(value));
                    } else if (field.getType() == RoomStatus.class){
                        field.set(object, RoomStatus.valueOf(value));
                    } else {
                        field.set(object, value);
                    }
                }

            }
        }
    }
}
