
public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();

        hotel.AddService("Massage", 100);
        hotel.ChangeCostService("Massage", 30);

        hotel.checkIntoRoom(new Client(321114, "Popov I.V."), new Client(123411, "Popova O.S."), new Client(431122, "Popov A.I."));
        hotel.checkIntoRoom(new Client(312122, "Frolov F.K."));
        hotel.checkIntoRoom(new Client(545412, "Petrov E.A."), new Client(545412, "Petrova F.Q."));

        hotel.evictFromRoom(2);

        hotel.ChangeCostRoom(2, 99);

        hotel.ChangeStatusRoom(4, "repair");

    }
}