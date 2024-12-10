package view;

import controller.HotelController;
import controller.ServiceController;

import java.util.Scanner;

public class MenuService {
    private final Scanner sc = new Scanner(System.in);
    private final ServiceController serviceController;
    private final ServiceView serviceView;

    public MenuService(ServiceController serviceController, ServiceView serviceView) {
        this.serviceController = serviceController;
        this.serviceView = serviceView;
    }

    public void printMenu() {
        boolean flag = true;
        while (flag) {
            serviceView.printMenu();

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

        serviceController.addService(serviceName, serviceCost);

        System.out.println("The service " + serviceName + " has been added to the hotel");
    }

    public void changeCostService() {
        System.out.println("Enter service name:");
        String serviceName = new Scanner(System.in).nextLine();
        System.out.println("Enter service cost:");
        int serviceCost = new Scanner(System.in).nextInt();

        serviceController.changeCostService(serviceName, serviceCost);

        System.out.println("The service cost " + serviceName + " has been changed to " + serviceCost);
    }
}
