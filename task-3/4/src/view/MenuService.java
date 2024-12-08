package view;

import controller.HotelController;
import controller.ServiceController;

import java.util.Scanner;

public class MenuService {
    private Scanner sc = new Scanner(System.in);
    ServiceController serviceController;
    HotelController hotelController;

    public MenuService(HotelController hotelController, ServiceController serviceController) {
        this.hotelController = hotelController;
        this.serviceController = serviceController;
    }
    public void printMenu() {
        boolean flag = true;
        while (flag) {
            System.out.println("\tMenu Service");
            System.out.println("1. Print services");
            System.out.println("2. Add service in hotel");
            System.out.println("3. Change service cost");
            System.out.println("4. Back");
            System.out.println("0. Exit");

            switch (sc.nextInt()) {
                case 1 -> serviceController.printServices();
                case 2 -> addService();
                case 3 -> changeCostService();
                case 4 -> flag = false;
                case 5 -> System.exit(0);
            }
        }
    }

    public void addService() {
        System.out.println("Enter service name:");
        String serviceName = new Scanner(System.in).nextLine();

        System.out.println("Enter service cost:");
        int serviceCost = new Scanner(System.in).nextInt();

        hotelController.addService(serviceName, serviceCost);
    }

    public void changeCostService() {
        System.out.println("Enter service name:");
        String serviceName = new Scanner(System.in).nextLine();
        System.out.println("Enter service cost:");
        int serviceCost = new Scanner(System.in).nextInt();

        serviceController.changeCostService(serviceName, serviceCost);
    }
}
