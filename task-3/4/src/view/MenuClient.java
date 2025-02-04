package view;

import controller.ClientController;
import controller.ExitController;
import controller.Inject;
import controller.SerializableController;
import controller.ServiceController;

import java.util.Scanner;

public class MenuClient{
    @Inject
    ClientController clientController;
    @Inject
    ServiceController serviceController;
    @Inject
    ClientView clientView;
    @Inject
    SerializableController serializableController;

    private final Scanner sc = new Scanner(System.in);

    public MenuClient(){
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
                case 5 -> {
                    try {
                        clientController.importFromCSV("clients.csv");
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 6 -> flag = false;
                case 0 -> ExitController.exit();
                default -> System.out.println("Invalid choice");
            }
        }

    }

    public void printClients() {
        if (clientController.clientsIsEmpty()) {
            System.out.println("There are no customers in the hotel");
        } else {
            boolean flag = true;
            while (flag) {
                clientView.printSwitchClient();

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
    }

    public void printClientServices() {
        if (clientController.clientsIsEmpty()) {
            System.out.println("There are no customers in the hotel");
        } else {
            int id = enterID();

            boolean flag = true;
            while (flag) {
                clientView.printSwitchClientService();

                switch (sc.nextInt()) {
                    case 1 -> {
                        clientController.printClientServices(id, "CostI");
                        flag = false;
                    }
                    case 2 -> {
                        clientController.printClientServices(id, "CostD");
                        flag = false;
                    }
                    case 3 -> {
                        clientController.printClientServices(id, "DateI");
                        flag = false;
                    }
                    case 4 -> {
                        clientController.printClientServices(id, "DateD");
                        flag = false;
                    }
                    case 5 -> flag = false;
                    default -> System.out.println("Invalid choice");
                }
            }
        }
    }

    public void addServiceForClient() {
        if (clientController.clientsIsEmpty()) {
            System.out.println("There are no customers in the hotel");
        } else if (serviceController.servicesIsEmpty()) {
            System.out.println("There are no services in the hotel");
        } else {
            int id = enterID();
            String date = enterDate();
            String serviceName = enterService();

            clientController.addServiceForClient(serviceName, id, date);
        }
    }

    public void printCostPerRoom() {
        if (clientController.clientsIsEmpty()) {
            System.out.println("There are no customers in the hotel");
        } else {
            int id = enterID();

            clientController.printCostPerRoom(id);
        }
    }

    public String enterDate() {
        while (true) {
            System.out.println("Enter date (dd-MM-yyyy):");
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

    public int enterID() {
        while (true) {
            System.out.println("Enter id client:");
            int id = new Scanner(System.in).nextInt();

            if (clientController.checkID(id)) {
                return id;
            }
            System.out.println("Invalid id");
        }
    }

}
