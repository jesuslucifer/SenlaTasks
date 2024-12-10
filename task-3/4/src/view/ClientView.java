package view;

import model.Client;
import model.Service;

import java.util.List;

public class ClientView {

    public void printMenu() {
        System.out.println("\tMenu Client");
        System.out.println("1. Print clients");
        System.out.println("2. Print client services");
        System.out.println("3. Add service for client");
        System.out.println("4. Print cost per room");
        System.out.println("5. Back");
        System.out.println("0. Exit");
    }

    public void printClients(List<Client> clients) {
        for (Client client : clients) {
            System.out.println(client.getFullName() + " " + client.getDateEvict() + " Room: " + client.getRoomNumber());
        }
    }

    public void printClientServices(List<Service> services) {
        for (Service service : services) {
            System.out.println(service.getServiceName() + " Cost: " + service.getCost() + " Date: "
                    + service.getServiceDate());
        }
    }

    public void printCostPerRoom(long cost) {
        System.out.println("Cost per room: " + cost);
    }
}
