package view;

import model.Client;
import model.Room;
import model.Service;

import java.util.List;

public class HotelView {



    public void printCountFreeRoom(int size) {
        System.out.println("Count free rooms: " +  size);
    }

    public void printCountClients(int size) {
        System.out.println("Count clients: " + size);
    }

    public void printRoomAndService(String typeSort, List<Room> rooms, List<Service> services) {
        switch (typeSort) {
            case "ChapterR":
                for (Room room : rooms) {
                    System.out.println("Room: " + room.getRoomNumber() + " Cost: " + room.getCost());
                }
                for (Service service : services) {
                    System.out.println("Service: " + service.getServiceName() + " Cost: " + service.getCost());
                }
                break;
            case "ChapterS", "Cost":
                for (Service service : services) {
                    System.out.println("Service: " + service.getServiceName() + " Cost: " + service.getCost());
                }
                for (Room room : rooms) {
                    System.out.println("Room: " + room.getRoomNumber() + " Cost: " + room.getCost());
                }
                break;
        }
    }


}
