package controller;

import model.Hotel;
import model.Service;
import java.util.List;

public class ServiceController {
    private final List<Service> services;

    public ServiceController(Hotel hotel) {
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
}
