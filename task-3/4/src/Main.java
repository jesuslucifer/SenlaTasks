import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();

        List<Client> clients = new ArrayList<>();

        hotel.addService("Massage", 100);
        hotel.addService("Bathhouse", 200);
        hotel.addService("Pizza", 300);
        hotel.addService("Bowling", 150);
        hotel.addService("Billiard", 220);

        hotel.changeCostService("Massage", 80);
        hotel.changeCostService("Bathhouse", 190);

        clients.add(new Client("321114", "Popov I.V."));
        clients.add(new Client("123411", "Popova O.S."));
        clients.add(new Client("431122", "Popov A.I."));
        hotel.checkIntoRoom(clients, "10-11-2024", "15-11-2024");
        clients.clear();

        hotel.addServiceForClient("Massage", "Popov I.V.", "13-11-2024");
        hotel.addServiceForClient("Bowling", "Popov I.V.", "14-11-2024");
        hotel.addServiceForClient("Pizza", "Popov I.V.", "12-11-2024");
        hotel.printClientServices("Kopov I.V.", "DateD");

        clients.add(new Client("545412", "Petrov E.A."));
        clients.add(new Client("545412", "Petrova F.Q."));
        hotel.checkIntoRoom(clients, "01-04-2024", "23-04-2024");
        clients.clear();

        clients.add(new Client("312122", "Frolov F.K."));
        hotel.checkIntoRoom(clients, "09-05-2024", "20-05-2024");
        clients.clear();

        clients.add(new Client("312122", "Gorbova F.A."));
        hotel.checkIntoRoom(clients, "20-03-2024", "21-03-2024");
        clients.clear();

        hotel.printCostPerRoom("Frolov F.K.");
        hotel.printCostPerRoom("Gorbova F.A.");
        hotel.printCostPerRoom("Popov I.V.");
        hotel.printCostPerRoom("Petrov E.A.");

        hotel.printRoomFreeByDate("09-05-2024");

        hotel.printRooms("CapacityI", "all");

        hotel.printCountFreeRoom();
        hotel.printCountClients();

        hotel.evictFromRoom(1);
        hotel.evictFromRoom(3);
        hotel.evictFromRoom(4);
        hotel.evictFromRoom(5);

        clients.add(new Client("312122", "Filatov A.F."));
        hotel.checkIntoRoom(clients, "28-06-2024", "29-06-2024");
        clients.clear();

        clients.add(new Client("312122", "Tripov L.A."));
        hotel.checkIntoRoom(clients, "01-07-2024", "30-07-2024");
        clients.clear();

        hotel.printCountFreeRoom();
        hotel.printCountClients();

        hotel.printInfoRoom(2);
        hotel.printHistoryRoom(1);

        hotel.printRoomAndService("Cost");

        hotel.printClientList("DateI");

        hotel.changeCostRoom(2, 99);
    }
}