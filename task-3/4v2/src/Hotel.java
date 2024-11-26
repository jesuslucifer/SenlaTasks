import java.util.List;
import java.util.ArrayList;

public class Hotel {
    private List<Room> rooms = new ArrayList<Room>();
    private List<Service> services = new ArrayList<Service>();
    private List<Client> clients = new ArrayList<Client>();
    private final int COUNT_ROOMS = 10;

    public Hotel() {
        for (int i = 0; i < COUNT_ROOMS; i++) {
            rooms.add(new Room(i));
        }
        System.out.println("The hotel is open, there are " + COUNT_ROOMS + " rooms available");
    }

    public void checkIntoRoom(int roomNumber) {
        for (Room room : rooms) {
            if ( (room.getRoomNumber() == roomNumber) && ( room.getStatus().equals("free") )  && (room.getCapacity() >= room.getClentList().size()) ) {
                room.setStatus("busy");
                System.out.println("The guest is accommodated in " + roomNumber + " room");
                break;
            } else if (room.getRoomNumber() == roomNumber && !room.getStatus().equals("free")) {
                System.out.println("The guest is not accommodated in " + roomNumber + " room is " + room.getStatus());
            }
        }
    }

    public void checkIntoRoom(int roomNumber, Client client) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.getStatus().equals("free") && room.getCapacity() >= room.getClentList().size()) {
                room.setClientList(client);
                System.out.println("The " + client.getFullName() + " is accommodated in " + roomNumber + " room");
                if ( room.getCapacity() == room.getClentList().size() ) {
                    room.setStatus("busy");
                }
                break;
            } else if (room.getRoomNumber() == roomNumber && !room.getStatus().equals("free")) {
                System.out.println("The guest is not accommodated in " + roomNumber + " room is " + room.getStatus());
            } else if (room.getRoomNumber() == roomNumber && room.getStatus().equals("free") && !( room.getCapacity() >= room.getClentList().size()) ){
                System.out.println("In room " + room.getRoomNumber() + " is not mest");
            }
        }
    }

    public void evictFromRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                room.setStatus("free");
                room.getClentList().clear();
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

    public void newClient(int passport, String fullName) {
        clients.add(new Client(passport, fullName));
    }

}
