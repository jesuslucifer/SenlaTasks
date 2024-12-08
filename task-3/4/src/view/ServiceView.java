package view;

import model.Service;

import java.util.List;

public class ServiceView {
    public void printServices(List<Service> services) {
        for (Service service : services) {
            System.out.println("Service: " + service.getServiceName() + " Cost: " + service.getCost());
        }
    }
}
