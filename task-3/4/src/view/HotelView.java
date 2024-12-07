package view;

import model.Client;
import model.Room;
import model.Service;
import java.util.Comparator;
import java.util.List;

public class HotelView {



    public void printCountFreeRoom(List<Room> rooms) {
        System.out.println("Count free rooms: " +  rooms.size());
    }

    public void printCountClients(List<Client> clients) {
        System.out.println("Count clients: " + clients.size());
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
            case "ChapterS":
                for (Service service : services) {
                    System.out.println("Service: " + service.getServiceName() + " Cost: " + service.getCost());
                }
                for (Room room : rooms) {
                    System.out.println("Room: " + room.getRoomNumber() + " Cost: " + room.getCost());
                }
                break;
            case "Cost":
                List<Room> list = rooms;
                List<Service> list2 = services;
                list.sort(Comparator.comparing(Room::getCost));
                list2.sort(Comparator.comparing(Service::getCost));
                for (Service service : list2) {
                    System.out.println("Service: " + service.getServiceName() + " Cost: " + service.getCost());
                }
                for (Room room : list) {
                    System.out.println("Room: " + room.getRoomNumber() + " Cost: " + room.getCost());
                }
                break;
        }
    }


}
