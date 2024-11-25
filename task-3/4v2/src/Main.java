
public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();

        hotel.AddService("Massage", 100);
        hotel.ChangeCostService("Massage", 30);

        hotel.checkIntoRoom(2);
        hotel.checkIntoRoom(4);

        hotel.evictFromRoom(2);

        hotel.ChangeCostRoom(2, 99);

        hotel.ChangeStatusRoom(4, "repair");
        hotel.checkIntoRoom(4);
    }
}