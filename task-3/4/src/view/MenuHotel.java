package view;

import controller.HotelController;

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
                case 4 -> flag = false;
                case 0 -> System.exit(0);
            }

        }
    }

    public void printRoomAndService() {
        System.out.println("Choose type sort:");
        System.out.println("1. Rooms then services");
        System.out.println("2. Services then room");
        System.out.println("3. Cost");

        switch (sc.nextInt()) {
            case 1 -> hotelController.printRoomAndService("ChapterR");
            case 2 -> hotelController.printRoomAndService("ChapterS");
            case 3-> hotelController.printRoomAndService("Cost");
        }
    }
}
