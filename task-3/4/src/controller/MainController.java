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
        Hotel hotel =  loadHotelFromSave();
        DI.register(Hotel.class, hotel);

        HotelView hotelView = new HotelView();
        DI.register(HotelView.class, hotelView);
        HotelController hotelController = new HotelController();
        DI.injectDependencies(hotelController);

        RoomView roomView = new RoomView();
        DI.register(RoomView.class, roomView);

        RoomController roomController = new RoomController();
        DI.injectDependencies(roomController);
        roomController.init();

        ClientView clientView = new ClientView();
        DI.register(ClientView.class, clientView);

        ClientController clientController = new ClientController();
        DI.injectDependencies(clientController);
        clientController.init();

        ServiceView serviceView = new ServiceView();
        DI.register(ServiceView.class, serviceView);

        ServiceController serviceController = new ServiceController();
        DI.injectDependencies(serviceController);
        serviceController.init();

        SerializableController serializableController = new SerializableController();
        DI.injectDependencies(serializableController);

        menu = new Menu(
                new MenuRoom(roomController, roomView, serializableController),
                new MenuService(serviceController, serviceView, serializableController),
                new MenuHotel(hotelController, hotelView, serializableController),
                new MenuClient(serviceController, clientController, clientView, serializableController), serializableController);
    }

    public void run() {
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
