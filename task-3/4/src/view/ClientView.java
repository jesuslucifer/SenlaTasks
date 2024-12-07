package view;

import model.Client;
import model.Service;

import java.util.List;

public class ClientView {

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
}
