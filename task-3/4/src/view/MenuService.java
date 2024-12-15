package view;

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
                case 4 -> {
                    try {
                        serviceController.importFromCSV("serviceCS.csv");
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 5 -> flag = false;
                case 0 -> System.exit(0);
                default -> System.out.println("Invalid choice");
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
        if (serviceController.servicesIsEmpty()) {
            System.out.println("There are no services in the hotel");
        } else {
            String serviceName = enterService();

            System.out.println("Enter service cost:");
            int serviceCost = new Scanner(System.in).nextInt();

            serviceController.changeCostService(serviceName, serviceCost);

            System.out.println("The service cost " + serviceName + " has been changed to " + serviceCost);
        }
    }

    public String enterService() {
        while (true) {
            System.out.println("Choose and enter service name:");
            serviceController.printServices();
            String serviceName = new Scanner(System.in).nextLine();

            if (serviceController.checkService(serviceName)) {
                return serviceName;
            }
            System.out.println("Invalid service");
        }
    }
}
