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
            }
        }

    }

    public void printClients() {
        System.out.println("Choose type sort:");
        System.out.println("Alphabet a-Z");
        System.out.println("Alphabet z-A");
        System.out.println("Date increase");
        System.out.println("Date decrease");

        switch (sc.nextInt()) {
            case 1 -> clientController.printClients("AlphabetA");
            case 2 -> clientController.printClients("AlphabetZ");
            case 3 -> clientController.printClients("DateI");
            case 4 -> clientController.printClients("DateD");
        }
    }

    public void printClientServices() {
        System.out.println("Enter full name client:");
        String fullName = new Scanner(System.in).nextLine();

        System.out.println("Choose type sort:");
        System.out.println("Cost increase");
        System.out.println("Cost decrease");
        System.out.println("Date increase");
        System.out.println("Date decrease");

        switch (sc.nextInt()) {
            case 1 -> clientController.printClientServices(fullName, "CostI");
            case 2 -> clientController.printClientServices(fullName, "CostD");
            case 3 -> clientController.printClientServices(fullName, "DateI");
            case 4 -> clientController.printClientServices(fullName, "DateD");
        }
    }

    public void addServiceForClient() {
        System.out.println("Enter full name client:");
        String fullName = new Scanner(System.in).nextLine();

        System.out.println("Enter date of service:");
        String date = new Scanner(System.in).nextLine();

        System.out.println("Choose and enter service:");
        serviceController.printServices();
        String serviceName = new Scanner(System.in).nextLine();

        clientController.addServiceForClient(serviceName, fullName, date);
    }

    public void printCostPerRoom() {
        System.out.println("Enter full name client:");
        String fullName = new Scanner(System.in).nextLine();

        clientController.printCostPerRoom(fullName);
    }

}
