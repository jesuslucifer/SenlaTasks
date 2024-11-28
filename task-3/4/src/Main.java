import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();

        hotel.printRooms("CostI", "free");
        System.out.println();
        hotel.printRooms("CostD", "all");
        System.out.println();
        hotel.printRooms("CapacityD", "free");
        System.out.println();
        hotel.printRooms("StarsD","all");
        System.out.println();
        hotel.printRooms("StarsI", "free");
        System.out.println();
        hotel.printRooms("dasdada", "all");

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

        System.out.println("111111111111111111111111");
        hotel.printRooms("CapacityD", "free");
        System.out.println();
        hotel.printRooms("CapacityD","all");


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