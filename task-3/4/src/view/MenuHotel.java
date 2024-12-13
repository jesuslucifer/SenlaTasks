package view;

import controller.HotelController;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class MenuHotel {
    private final HotelController hotelController;
    private final Scanner sc = new Scanner(System.in);
    private final HotelView hotelView;

    public MenuHotel(HotelController hotelController, HotelView hotelView) {
        this.hotelController = hotelController;
        this.hotelView = hotelView;
    }

    public void printMenu() {
        boolean flag = true;
        while (flag) {
            hotelView.printMenu();

            switch (sc.nextInt()) {
                case 1 -> hotelController.printCountFreeRoom();
                case 2 -> hotelController.printCountClients();
                case 3 -> printRoomAndService();
                case 4 -> {
                    try {
                        hotelController.exportToCSV(hotelController.getClients(), "clients.csv");
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 5 -> {
                    try {
                        hotelController.exportToCSV(hotelController.getRooms(), "rooms.csv");
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 6-> {
                    try {
                        hotelController.exportToCSV(hotelController.getServices(), "services.csv");
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 7 -> flag = false;
                case 0 -> System.exit(0);
                default -> System.out.println("Invalid choice");
            }

        }
    }

    public void printRoomAndService() {
        boolean flag = true;
        while (flag) {
            hotelView.printSwitchRoomService();

            switch (sc.nextInt()) {
                case 1 -> {
                    hotelController.printRoomAndService("ChapterR");
                    flag = false;
                }
                case 2 -> {
                    hotelController.printRoomAndService("ChapterS");
                    flag = false;
                }
                case 3 -> {
                    hotelController.printRoomAndService("Cost");
                    flag = false;
                }
                case 4 -> flag = false;
                default -> System.out.println("Invalid choice");
            }
        }
    }
}
