package controller;

import view.Menu;
import model.Hotel;

import javax.imageio.IIOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MainController {
    @Inject
    Hotel hotel;
    @Inject
    Menu menu;
    @Inject
    RoomController roomController;
    @Inject
    ClientController clientController;
    @Inject
    ServiceController serviceController;


    public MainController() {
    }

    public void run() {
        hotel = loadHotelFromSave();
        DI.register(Hotel.class, hotel);
        DI.injectDependencies(this);
        roomController.init();
        clientController.init();
        serviceController.init();
        menu.printMenu();
    }

    public Hotel loadHotelFromSave() {
        try (ObjectInputStream os = new ObjectInputStream(new FileInputStream("task-3/4/src/resources/save.dat"))) {
            return (Hotel) os.readObject();
        } catch (FileNotFoundException | IIOException | ClassNotFoundException e) {
            return new Hotel();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
