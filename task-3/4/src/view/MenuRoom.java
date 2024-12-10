package view;

import controller.RoomController;
import model.Client;
import model.RoomStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuRoom {
    private RoomController roomController;
    private Scanner sc = new Scanner(System.in);

    public MenuRoom(RoomController roomController) {
        this.roomController = roomController;
    }

    public void printMenu() {
        boolean flag = true;
        while (flag) {
            System.out.println("\tMenu Room");
            System.out.println("1. Print all rooms");
            System.out.println("2. Print free rooms");
            System.out.println("3. Print info room");
            System.out.println("4. Check into room");
            System.out.println("5. Evict from room");
            System.out.println("6. Print history room");
            System.out.println("7. Print room free by date");
            System.out.println("8. Change status of room");
            System.out.println("9. Change cost of room");
            System.out.println("10. Back");
            System.out.println("0. Exit");

            switch (sc.nextInt()) {
                case 1 -> printRooms("all");
                case 2 -> printRooms("free");
                case 3 -> printInfoRoom();
                case 4 -> checkIntoRoom();
                case 5 -> evictFromRoom();
                case 6 -> printHistoryRoom();
                case 7 -> printRoomFreeByDate();
                case 8 -> changeStatus();
                case 9 -> changeCost();
                case 10 -> flag = false;
                case 0 -> System.exit(0);
            }
        }

    }

    public void printRooms(String roomType) {
        System.out.println("Choose type sort:");
        System.out.println("1. Capacity increase");
        System.out.println("2. Capacity decrease");
        System.out.println("3. Cost increase");
        System.out.println("4. Cost decrease");
        System.out.println("5. Stars increase");
        System.out.println("6. Stars decrease");

        switch (sc.nextInt()) {
            case 1 -> roomController.printRooms("CapacityI", roomType);
            case 2 -> roomController.printRooms("CapacityD", roomType);
            case 3 -> roomController.printRooms("CostI", roomType);
            case 4 -> roomController.printRooms("CostD", roomType);
            case 5 -> roomController.printRooms("StarsI", roomType);
            case 6 -> roomController.printRooms("StarsD", roomType);
        }

    }

    public void printInfoRoom() {
        roomController.printInfoRoom(enterRoomNumber());
    }

    public void printHistoryRoom() {
        roomController.printHistoryRoom(enterRoomNumber());
    }

    public void printRoomFreeByDate() {
        roomController.printRoomFreeByDate(enterDate("Just"));
    }

    public void checkIntoRoom() {
        int roomNumber = enterRoomNumber();
        int countClients = enterCountClients(roomNumber);

        Client client = new Client();
        client.setDateCheckIn(enterDate("CheckIn"));
        client.setDateEvict(enterDate("Evict"));

        List<Client> clientList = new ArrayList<>();
        for (int i = 0; i < countClients; i++) {
            Client client1 = new Client();
            System.out.println("Enter full name client:");
            client1.setFullName(new Scanner(System.in).nextLine());
            client1.setRoomNumber(roomNumber);
            clientList.add(client1);
        }

        roomController.checkIntoRoom(clientList, roomNumber, client.getDateCheckIn(), client.getDateEvict());
    }

    public void evictFromRoom() {
        roomController.evictFromRoom(enterRoomNumber());
    }

    public void changeStatus() {
        int roomNumber = enterRoomNumber();

        System.out.println("Choose status:");
        System.out.println("1. Free");
        System.out.println("2. Busy");
        System.out.println("3. Repaired");

        switch (sc.nextInt()) {
            case 1 -> roomController.changeStatusRoom(roomNumber, RoomStatus.FREE);
            case 2 -> roomController.changeStatusRoom(roomNumber, RoomStatus.BUSY);
            case 3 -> roomController.changeStatusRoom(roomNumber, RoomStatus.REPAIRED);
        }
    }

    public void changeCost() {
        int roomNumber = enterRoomNumber();

        System.out.println("Enter cost");
        int cost = new Scanner(System.in).nextInt();

        roomController.changeCostRoom(roomNumber, cost);
    }

    public int enterRoomNumber() {
        while (true) {
            System.out.println("Enter room number:");
            int roomNumber = new Scanner(System.in).nextInt();

            if (roomController.checkRoom(roomNumber)) {
                return roomNumber;
            }

            System.out.println("Invalid room number");
        }
    }

    public int enterCountClients(int roomNumber) {
        while (true) {
            System.out.println("Enter count clients:");
            int countClients = new Scanner(System.in).nextInt();

            if (roomController.checkCapacityRoom(roomNumber, countClients)) {
                return countClients;
            }

            System.out.println("Invalid count clients");
        }
    }

    public String enterDate(String typeDate) {
        switch (typeDate) {
            case "CheckIn" -> {
                while (true) {
                    System.out.println("Enter date check in (dd-MM-yyyy):");
                    String date = new Scanner(System.in).nextLine();
                    if (roomController.checkDate(date)) {
                        return date;
                    }
                    System.out.println("Invalid date check in");
                }
            }
            case "Evict" -> {
                while (true) {
                    System.out.println("Enter date evict (dd-MM-yyyy):");
                    String date = new Scanner(System.in).nextLine();
                    if (roomController.checkDate(date)) {
                        return date;
                    }
                    System.out.println("Invalid date evict");
                }
            }
        case "Just" -> {
            while (true) {
                System.out.println("Enter date (dd-MM-yyyy):");
                String date = new Scanner(System.in).nextLine();
                if (roomController.checkDate(date)) {
                    return date;
                }
                System.out.println("Invalid date");
            }
        }
        }
        return "Invalid";
    }
}
