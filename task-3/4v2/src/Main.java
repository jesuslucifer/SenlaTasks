
public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();

        hotel.AddService("Massage", 100);
        hotel.ChangeCostService("Massage", 30);

        /*hotel.newClient(190223, "Komarov");
        hotel.newClient(12345, "Popov");
        hotel.newClient(12345, "Pustov");*/

        hotel.checkIntoRoom(3, new Client(190223, "Komarov"));
        hotel.checkIntoRoom(3, new Client(313131, "Popov"));
        hotel.checkIntoRoom(3, new Client(413112, "Petrov"));
        //hotel.checkIntoRoom(2);
        //hotel.checkIntoRoom(4);

        hotel.evictFromRoom(2);

        hotel.ChangeCostRoom(2, 99);

        hotel.ChangeStatusRoom(4, "repair");
        hotel.checkIntoRoom(4);
    }
}