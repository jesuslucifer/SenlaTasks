package controller;

import view.ClientView;
import view.HotelView;
import view.Menu;
import view.MenuClient;
import view.MenuHotel;
import view.MenuRoom;
import view.MenuService;
import view.RoomView;
import view.ServiceView;
import model.Hotel;

import javax.imageio.IIOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MainController {
    private final Menu menu;

    public MainController() throws IOException {
        Hotel hotel;
        try (ObjectInputStream os = new ObjectInputStream(new FileInputStream("task-3/4/src/resources/save.dat"))) {
            hotel = (Hotel) os.readObject();
        } catch (FileNotFoundException | IIOException | ClassNotFoundException e) {
            hotel = new Hotel();
        }

        HotelView hotelView = new HotelView();
        HotelController hotelController = new HotelController(hotel, hotelView);

        RoomView roomView = new RoomView();
        RoomController roomController = new RoomController(hotel, roomView);

        ClientView clientView = new ClientView();
        ClientController clientController = new ClientController(hotel, clientView);

        ServiceView serviceView = new ServiceView();
        ServiceController serviceController = new ServiceController(hotel, serviceView);

        SerializableController serializableController = new SerializableController(hotel);

        menu = new Menu(
                new MenuRoom(roomController, roomView, serializableController),
                new MenuService(serviceController, serviceView, serializableController),
                new MenuHotel(hotelController, hotelView, serializableController),
                new MenuClient(serviceController, clientController, clientView, serializableController), serializableController);
    }

    public void run() {
        menu.printMenu();
    }
}
