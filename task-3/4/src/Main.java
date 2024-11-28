import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();

        hotel.printRooms("CostI");
        System.out.println();
        hotel.printRooms("CostD");
        System.out.println();
        hotel.printRooms("CapacityD");
        System.out.println();
        hotel.printRooms("StarsD");
        System.out.println();
        hotel.printRooms("StarsI");
        System.out.println();
        hotel.printRooms("dasdada");

        List<Client> clients = new ArrayList<>();

        hotel.addService("Massage", 100);
        hotel.changeCostService("Massage", 30);

        clients.add(new Client("321114", "Popov I.V."));
        clients.add(new Client("123411", "Popova O.S."));
        clients.add(new Client("431122", "Popov A.I."));
        hotel.checkIntoRoom(clients);
        clients.clear();

        clients.add(new Client("545412", "Petrov E.A."));
        clients.add(new Client("545412", "Petrova F.Q."));
        hotel.checkIntoRoom(clients);
        clients.clear();

        clients.add(new Client("312122", "Frolov F.K."));
        hotel.checkIntoRoom(clients);
        clients.clear();

        hotel.evictFromRoom(1);
        hotel.evictFromRoom(3);
        hotel.evictFromRoom(4);
        hotel.evictFromRoom(5);

        hotel.changeCostRoom(2, 99);

        hotel.changeStatusRoom(4, "repair");

    }
}