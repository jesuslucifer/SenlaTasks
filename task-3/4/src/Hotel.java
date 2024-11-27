import java.util.List;
import java.util.ArrayList;

public class Hotel {
    private List<Room> rooms = new ArrayList<>();
    private List<Service> services = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();
    private final int COUNT_ROOMS = 10;

    public Hotel() {
        for (int i = 0; i < COUNT_ROOMS; i++) {
            rooms.add(new Room(i));
        }
        System.out.println("The hotel is open, there are " + COUNT_ROOMS + " rooms available");
    }

    public void checkIntoRoom(List<Client> buffClients) {
        boolean found = false;
        for (Room room : rooms) {
            if (room.getCapacity() >= buffClients.size() && room.getStatus().equals("free")) {
                room.setClientList(buffClients);
                clients.addAll(buffClients);
                found = true;
                room.setStatus("busy");
                System.out.println("The clients is accommodated in " + room.getRoomNumber() + " room");
                break;
            }
        }
        if (!found) {
            System.out.println("There are no places in the hotel");
        }
    }

    public void evictFromRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                room.setStatus("free");
                clients.removeAll(room.getClentList());
                room.getClentList().clear();
            }
        }
        System.out.println("The guest has been evicted from the " + roomNumber + " room");
    }

    public void changeStatusRoom(int roomNumber, String status) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                room.setStatus(status);
            }
        }
        System.out.println("The status of the room " + roomNumber + " has been changed to " + status);
    }

    public void changeCostRoom(int roomNumber, int cost) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                room.setCost(cost);
            }
        }
        System.out.println("The room cost has been changed to " + cost);
    }

    public void addService(String serviceName, int cost) {
        services.add(new Service(serviceName, cost));
        System.out.println("The service " + serviceName + " has been added to the hotel");
    }

    public void changeCostService(String serviceName, int cost) {
        for (Service service : services) {
            if (service.getServiceName().equals(serviceName)) {
                service.setCost(cost);
            }
        }
        System.out.println("The service cost " + serviceName + " has been changed to " + cost);
    }

}
