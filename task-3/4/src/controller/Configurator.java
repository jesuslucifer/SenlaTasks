package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

public class Configurator {
    public void configure(Object object) throws Exception {
        Class<?> clazz = object.getClass();
        Properties properties = new Properties();

        try(FileInputStream fis = new FileInputStream("task-3/4/src/resources/annotationConfig.property")) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(Field field : clazz.getDeclaredFields()) {
            ConfigProperty annotation = field.getAnnotation(ConfigProperty.class);
            if(annotation != null) {
                String value = properties.getProperty(annotation.propertyName());

                field.setAccessible(true);
                if(value != null) {
                    System.out.println(field.getName() + " : " + value + " : " + field.getType());
                    if(field.getType() == Integer.class || field.getType() == int.class) {
                        field.set(object, Integer.parseInt(value));
                    } else {
                        field.set(object, value);
                    }
                }
            }
        }
    }
}
