
public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();

        hotel.addService("Massage", 100);
        hotel.changeCostService("Massage", 30);

        hotel.addClient(new Client("321114", "Popov I.V."));
        hotel.addClient(new Client("123411", "Popova O.S."));
        hotel.addClient(new Client("431122", "Popov A.I."));
        hotel.checkIntoRoom();

        hotel.addClient(new Client("545412", "Petrov E.A."));
        hotel.addClient(new Client("545412", "Petrova F.Q."));
        hotel.checkIntoRoom();

        hotel.addClient(new Client("312122", "Frolov F.K."));
        hotel.checkIntoRoom();

        hotel.evictFromRoom(1);
        hotel.evictFromRoom(3);
        hotel.evictFromRoom(4);
        hotel.evictFromRoom(5);

        hotel.changeCostRoom(2, 99);

        hotel.changeStatusRoom(4, "repair");

    }
}