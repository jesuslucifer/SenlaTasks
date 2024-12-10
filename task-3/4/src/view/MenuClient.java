package view;

import controller.ClientController;
import controller.ServiceController;

import java.util.Scanner;

public class MenuClient{
    private final ClientController clientController;
    private final ServiceController serviceController;
    private final ClientView clientView;

    private final Scanner sc = new Scanner(System.in);

    public MenuClient(ServiceController serviceController, ClientController clientController, ClientView clientView){
        this.serviceController = serviceController;
        this.clientController = clientController;
        this.clientView = clientView;
    }

    public void printMenu() {
        boolean flag = true;
        while (flag) {
            clientView.printMenu();

            switch (sc.nextInt()) {
                case 1 -> printClients();
                case 2 -> printClientServices();
                case 3 -> addServiceForClient();
                case 4 -> printCostPerRoom();
                case 5 -> flag = false;
                case 0 -> System.exit(0);
                default -> System.out.println("Invalid choice");
            }
        }

    }

    public void printClients() {
        boolean flag = true;
        while (flag) {
            System.out.println("\tChoose type sort:");
            System.out.println("1. Alphabet a-Z");
            System.out.println("2. Alphabet z-A");
            System.out.println("3. Date increase");
            System.out.println("4. Date decrease");
            System.out.println("5. Back");

            switch (sc.nextInt()) {
                case 1 -> {
                    clientController.printClients("AlphabetA");
                    flag = false;
                }
                case 2 -> {
                    clientController.printClients("AlphabetZ");
                    flag = false;
                }
                case 3 -> {
                    clientController.printClients("DateI");
                    flag = false;
                }
                case 4 -> {
                    clientController.printClients("DateD");
                    flag = false;
                }
                case 5 -> flag = false;
                default -> System.out.println("Invalid choice");
            }
        }
    }

    public void printClientServices() {
        String fullName = enterFullName();

        boolean flag = true;
        while (flag) {
            System.out.println("\tChoose type sort:");
            System.out.println("1. Cost increase");
            System.out.println("2. Cost decrease");
            System.out.println("3. Date increase");
            System.out.println("4. Date decrease");
            System.out.println("5. Back");

            switch (sc.nextInt()) {
                case 1 -> {
                    clientController.printClientServices(fullName, "CostI");
                    flag = false;
                }
                case 2 -> {
                    clientController.printClientServices(fullName, "CostD");
                    flag = false;
                }
                case 3 -> {
                    clientController.printClientServices(fullName, "DateI");
                    flag = false;
                }
                case 4 -> {
                    clientController.printClientServices(fullName, "DateD");
                    flag = false;
                }
                case 5 -> flag = false;
                default -> System.out.println("Invalid choice");
            }
        }
    }

    public void addServiceForClient() {
        String fullName = enterFullName();
        String date = enterDate();
        String serviceName = enterService();

        clientController.addServiceForClient(serviceName, fullName, date);
    }

    public void printCostPerRoom() {
        String fullName = enterFullName();

        clientController.printCostPerRoom(fullName);
    }

    public String enterDate() {
        while (true) {
            System.out.println("Enter date check in (dd-MM-yyyy):");
            String date = new Scanner(System.in).nextLine();
            if (clientController.checkDate(date)) {
                return date;
            }
            System.out.println("Invalid date check in");
        }
    }

    public String enterService() {
        while (true) {
            System.out.println("Choose and enter service name:");
            serviceController.printServices();
            String serviceName = new Scanner(System.in).nextLine();

            if (clientController.checkService(serviceName)) {
                return serviceName;
            }
            System.out.println("Invalid service");
        }
    }

    public String enterFullName() {
        while (true) {
            System.out.println("Enter full name client:");
            String fullName = new Scanner(System.in).nextLine();

            if (clientController.checkFullName(fullName)) {
                return fullName;
            }
            System.out.println("Invalid full name");
        }
    }

}
