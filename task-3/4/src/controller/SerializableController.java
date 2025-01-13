package controller;

import model.Hotel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializableController {
    @Inject
    Hotel hotel;

    public SerializableController() {
        DI.injectDependencies(this);
    }

    public void exit() {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("task-3/4/src/resources/save.dat"))) {
            oos.writeObject(hotel);
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
