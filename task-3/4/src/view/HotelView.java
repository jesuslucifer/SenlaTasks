package view;

import model.Client;
import model.Room;
import model.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class HotelView {

    public void printRooms(String typeSort, String typeRoom, List<Room> rooms, List<Room> freeRooms){
        List<Room> list = rooms;

        if (typeRoom.equals("free"))
        {
            list = freeRooms;
        }

        switch (typeSort) {
            case "CapacityI":
                list.sort(Comparator.comparing(Room::getCapacity));
                break;
            case "CapacityD":
                list.sort(Comparator.comparing(Room::getCapacity).reversed());
                break;
            case "CostI":
                list.sort(Comparator.comparing(Room::getCost));
                break;
            case "CostD":
                list.sort(Comparator.comparing(Room::getCost).reversed());
                break;
            case "StarsI":
                list.sort(Comparator.comparing(Room::getCountStars));
                break;
            case "StarsD":
                list.sort(Comparator.comparing(Room::getCountStars).reversed());
                break;
            default:
                list.sort(Comparator.comparing(Room::getRoomNumber));
                break;
        }
        for (Room room : list) {
            System.out.println("Room: " + room.getRoomNumber() + " Status: " + room.getStatus() + " Stars: " + room.getCountStars() + " Capacity: " + room.getCapacity() + " Cost: " + room.getCost());
        }
    }

    public void printInfoRoom(int roomNumber, List<Room> rooms) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                if (room.isBusy()) {
                    System.out.println("Room: " + room.getRoomNumber() + " Status: " + room.getStatus() + " Stars: " + room.getCountStars() + " Capacity: " + room.getCapacity() + " Cost: " + room.getCost());
                    System.out.print("Clients: ");
                    for (Client client : room.getClentList()) {
                        System.out.print(client.getFullName() + " ");
                    }
                    System.out.println();
                } else {
                    System.out.println("Room: " + room.getRoomNumber() + " Status: " + room.getStatus() + " Stars: " + room.getCountStars() + " Capacity: " + room.getCapacity() + " Cost: " + room.getCost());
                }
            }
        }
    }

    public void printCountFreeRoom(List<Room> rooms) {
        System.out.println("Count free rooms: " +  rooms.size());
    }

    public void printCostPerRoom(String fullName, List<Client> clients, List<Room> rooms) {
        for (Client client : clients) {
            if (client.getFullName().equals(fullName)) {
                long daysBetween = DAYS.between(client.getDateCheckIn(), client.getDateEvict());
                for (Room room : rooms) {
                    if (room.getRoomNumber() == client.getRoomNumber()) {
                        System.out.println("Cost per room: " + daysBetween * room.getCost());
                    }
                }
            }
        }
    }

    public void printCountClients(List<Client> clients) {
        System.out.println("Count clients: " + clients.size());
    }

    public void printHistoryRoom(int roomNumber, List<Room> rooms) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                System.out.println("Room " + room.getRoomNumber() + " history:");
                int i = 3;
                Deque<Client> deque = room.getHistoryClientQueue();
                while (i != 0 && !deque.isEmpty()) {
                    System.out.print(deque.pollLast().getFullName() + " ");
                    i--;
                }
                System.out.println();
            }
        }
    }

    public void printRoomFreeByDate(LocalDate date, List<Room> rooms) {
        System.out.println("Free rooms by " + date);
        for (Room room : rooms) {
            if (room.getDateEvict().isBefore(date)) {
                System.out.println("Room: " + room.getRoomNumber());
            }
        }
    }

    public void printClientList(String typeSort, List<Client> clients) {
        List<Client> list = clients;

        switch (typeSort) {
            case "AlphabetA":
                list.sort(Comparator.comparing(Client::getFullName));
                break;
            case "AlphabetZ":
                list.sort(Comparator.comparing(Client::getFullName).reversed());
                break;
            case "DateI":
                list.sort(Comparator.comparing(Client::getDateEvict));
                break;
            case "DateD":
                list.sort(Comparator.comparing(Client::getDateEvict).reversed());
                break;
            default:
                break;
        }
        for (Client client : list) {
            System.out.println(client.getFullName() + " " + client.getDateEvict() + " Room: " + client.getRoomNumber());
        }
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

    public void printClientServices(String fullName, String typeSort, List<Client> clients) {
        for (Client client : clients) {
            if (client.getFullName().equals(fullName)) {
                System.out.println("Services " + client.getFullName() + ":");
                List<Service> list = client.getServices();
                switch (typeSort) {
                    case "CostI":
                        list.sort(Comparator.comparing(Service::getCost));
                        break;
                    case "CostD":
                        list.sort(Comparator.comparing(Service::getCost).reversed());
                        break;
                    case "DateI":
                        list.sort(Comparator.comparing(Service::getServiceDate));
                        break;
                    case "DateD":
                        list.sort(Comparator.comparing(Service::getServiceDate).reversed());
                        break;
                    default:
                        break;
                }
                for (Service service : list) {
                    System.out.println(service.getServiceName() + " Cost: " + service.getCost() + " Date: " + service.getServiceDate());
                }
                break;
            }
        }
    }
}
