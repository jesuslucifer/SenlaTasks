import java.util.List;
import java.util.ArrayList;

public class Hotel {
    private List<Room> rooms = new ArrayList<Room>();
    private List<Service> services = new ArrayList<Service>();

    public Hotel() {
        for (int i = 0; i < 10; i++) {
            rooms.add(new Room(i));
        }
        System.out.println("The hotel is open, there are 10 rooms available");
    }

    public void checkIntoRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.getStatus().equals("free")) {
                room.setStatus("busy");
                System.out.println("The guest is accommodated in " + roomNumber + " room");
                break;
            } else if (room.getRoomNumber() == roomNumber && !room.getStatus().equals("free")) {
                System.out.println("The guest is not accommodated in " + roomNumber + " room is " + room.getStatus());
            }
        }
    }

    public void evictFromRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                room.setStatus("free");
            }
        }
        System.out.println("The guest has been evicted from the " + roomNumber + " room");
    }

    public void ChangeStatusRoom(int roomNumber, String status) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                room.setStatus(status);
            }
        }
        System.out.println("The status of the room " + roomNumber + " has been changed to " + status);
    }

    public void ChangeCostRoom(int roomNumber, int cost) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                room.setCost(cost);
            }
        }
        System.out.println("The room cost has been changed to " + cost);
    }

    public void AddService(String serviceName, int cost) {
        services.add(new Service(serviceName, cost));
        System.out.println("The service " + serviceName + " has been added to the hotel");
    }

    public void ChangeCostService(String serviceName, int cost) {
        for (Service service : services) {
            if (service.getServiceName().equals(serviceName)) {
                service.setCost(cost);
            }
        }
        System.out.println("The service cost " + serviceName + " has been changed to " + cost);
    }
}
