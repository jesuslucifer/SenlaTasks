package view;

import controller.ClientController;
import controller.HotelController;
import controller.ServiceController;

import java.util.Scanner;

public class MenuClient{
    private ClientController clientController;
    private ServiceController serviceController;

    private Scanner sc = new Scanner(System.in);

    public MenuClient(ServiceController serviceController, ClientController clientController){
        this.serviceController = serviceController;
        this.clientController = clientController;
    }
    public void printMenu() {
        boolean flag = true;
        while (flag) {
            System.out.println("\tMenu Client");
            System.out.println("1. Print clients");
            System.out.println("2. Print client services");
            System.out.println("3. Add service for client");
            System.out.println("4. Back");
            System.out.println("0. Exit");

            switch (sc.nextInt()) {
                case 1 -> printClients();
                case 2 -> printClientServices();
                case 3 -> addServiceForClient();
                case 4 -> flag = false;
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

        clientController.addServiceForClient(fullName, serviceName, date);
    }

}
