package view;

import model.Room;
import model.Service;

import java.util.List;

public class HotelView {

    public void printMenu() {
        System.out.println("\tMenu Hotel");
        System.out.println("1. Print count free rooms");
        System.out.println("2. Print count clients");
        System.out.println("3. Print rooms and services");
        System.out.println("4. Export clients");
        System.out.println("5. Export rooms");
        System.out.println("6. Export services");
        System.out.println("7. Back");
        System.out.println("0. Exit");
    }

    public void printSwitchRoomService() {
        System.out.println("\tChoose type sort:");
        System.out.println("1. Rooms then services");
        System.out.println("2. Services then room");
        System.out.println("3. Cost");
        System.out.println("4. Back");
    }

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
