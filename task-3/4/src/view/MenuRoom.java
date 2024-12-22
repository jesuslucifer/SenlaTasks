package view;

import controller.RoomController;
import model.Client;
import model.RoomStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuRoom {
    private final RoomController roomController;
    private final RoomView roomView;
    private final Scanner sc = new Scanner(System.in);

    public MenuRoom(RoomController roomController, RoomView roomView) {
        this.roomController = roomController;
        this.roomView = roomView;
    }

    public void printMenuPageOne() {
        boolean flag = true;
        while (flag) {
            roomView.printMenuPageOne();

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
                case 10 -> {
                    try {
                        roomController.importFromCSV("rooms.csv");
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 11 -> flag = false;
                case 0 -> roomController.exit();
                default -> System.out.println("Invalid choice");
            }
        }

    }

//    public void printMenuPageTwo() {
//        boolean flag = true;
//        while (flag) {
//            roomView.printMenuPageTwo();
//
//            switch (sc.nextInt()) {
//                case 4 -> flag = false;
//                case 0 -> roomController.exit();
//                default -> System.out.println("Invalid choice");
//            }
//        }
//    }

    public void printRooms(String roomType) {
        boolean flag = true;
        while (flag) {
            roomView.printSwitchRooms();

            switch (sc.nextInt()) {
                case 1 -> {
                    roomController.printRooms("CapacityI", roomType);
                    flag = false;
                }
                case 2 -> {
                    roomController.printRooms("CapacityD", roomType);
                    flag = false;
                }
                case 3 -> {
                    roomController.printRooms("CostI", roomType);
                    flag = false;
                }
                case 4 -> {
                    roomController.printRooms("CostD", roomType);
                    flag = false;
                }
                case 5 -> {
                    roomController.printRooms("StarsI", roomType);
                    flag = false;
                }
                case 6 -> {
                    roomController.printRooms("StarsD", roomType);
                    flag = false;
                }
                case 7 -> flag = false;
                default -> System.out.println("Invalid choice");
            }
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

        boolean flag = true;
        while (flag) {
            roomView.printSwitchStatus();

            switch (sc.nextInt()) {
                case 1 -> {
                    roomController.changeStatusRoom(roomNumber, RoomStatus.FREE);
                    flag = false;
                }
                case 2 -> {
                    roomController.changeStatusRoom(roomNumber, RoomStatus.BUSY);
                    flag = false;
                }
                case 3 -> {
                    roomController.changeStatusRoom(roomNumber, RoomStatus.REPAIRED);
                    flag = false;
                }
                case 4 -> flag = false;
                default -> System.out.println("Invalid choice");
            }
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
