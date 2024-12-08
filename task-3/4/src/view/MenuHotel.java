package view;

import controller.HotelController;

import java.util.Scanner;

public class MenuHotel {
    private HotelController hotelController;
    private Scanner sc = new Scanner(System.in);

    public MenuHotel(HotelController hotelController) {
        this.hotelController = hotelController;
    }

    public void printMenu() {
        boolean flag = true;
        while (flag) {
            System.out.println("\tMenu Hotel");
            System.out.println("1. Add service");
            System.out.println("2. Print count free rooms");
            System.out.println("3. Print count clients");
            System.out.println("4. Print rooms and services");
            System.out.println("5. Back");
            System.out.println("0. Exit");

            switch (sc.nextInt()) {
                case 1 -> addService();
                case 2 -> hotelController.printCountFreeRoom();
                case 3 -> hotelController.printCountClients();
                case 4 -> printRoomAndService();
                case 5 -> flag = false;
                case 6 -> System.exit(0);
            }

        }
    }

    public void addService() {
        System.out.println("Enter service name: ");
        String serviceName = new Scanner(System.in).nextLine();
        System.out.println("Enter service cost: ");
        int serviceCost = new Scanner(System.in).nextInt();

        hotelController.addService(serviceName, serviceCost);
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
