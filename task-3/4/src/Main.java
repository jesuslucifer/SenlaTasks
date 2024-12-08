import controller.ClientController;
import controller.HotelController;
import controller.RoomController;
import controller.ServiceController;
import model.Hotel;
import view.ClientView;
import view.HotelView;
import view.Menu;
import view.MenuClient;
import view.MenuHotel;
import view.MenuRoom;
import view.MenuService;
import view.RoomView;
import view.ServiceView;


public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        HotelView hotelView = new HotelView();
        HotelController hotelController = new HotelController(hotel, hotelView);

        ClientView clientView = new ClientView();
        ClientController clientController = new ClientController(hotel, clientView);

        RoomView roomView = new RoomView();
        RoomController roomController = new RoomController(hotel, roomView);

        ServiceView serviceView = new ServiceView();
        ServiceController serviceController = new ServiceController(serviceView, hotel);

        Menu menu = new Menu(new MenuRoom(roomController), new MenuService(hotelController, serviceController), new MenuHotel(hotelController), new MenuClient(serviceController, clientController));
        menu.printMenu();
        menu.selectMenu();
    }
}