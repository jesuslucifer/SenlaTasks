package view;

import model.Service;

import java.util.List;

public class ServiceView {
    public void printMenu() {
        System.out.println("\tMenu Service");
        System.out.println("1. Print services");
        System.out.println("2. Add service in hotel");
        System.out.println("3. Change service cost");
        System.out.println("4. Import services");
        System.out.println("5. Back");
        System.out.println("0. Exit");
    }

    public void printServices(List<Service> services) {
        for (Service service : services) {
            System.out.println("Service: " + service.getServiceName() + " Cost: " + service.getCost() + " ID: " + service.getId());
        }
    }
}
