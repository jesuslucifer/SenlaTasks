package controller;

import model.Hotel;
import model.Service;
import view.ServiceView;
import java.util.List;

public class ServiceController {
    private final List<Service> services;
    private ServiceView view;

    public ServiceController(ServiceView view, Hotel hotel) {
        this.view = view;
        this.services = hotel.getServices();
    }

    public Service getService(String serviceName) {
        for (Service service : services) {
            if (service.getServiceName().equals(serviceName)) {
                return service;
            }
        }
        return null;
    }

    public void changeCostService(String serviceName, int cost) {
        getService(serviceName).setCost(cost);
    }

    public void printServices() {
        view.printServices(services);
    }

    public void addService(String serviceName, int cost) {
        services.add(new Service(serviceName, cost));
    }
}
