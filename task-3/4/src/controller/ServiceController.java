package controller;

import model.Hotel;
import model.Service;
import view.ServiceView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class ServiceController {
    private final List<Service> services;
    private final ServiceView view;

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

    public boolean checkService(String service) {
        for (Service service1 : services) {
            if (service1.getServiceName().equals(service)) {
                return true;
            }
        }
        return false;
    }

    public boolean servicesIsEmpty() {
        return services.isEmpty();
    }

    public void importFromCSV(String fileName) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] split = line.split(",");
                Service importService = new Service(
                        Integer.parseInt(split[0]),
                        split[1],
                        Integer.parseInt(split[2]));

                boolean found = false;
                for (Service service : services) {
                    if (service.getId() == importService.getId()) {
                        service.updateFromCSV(split);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    services.add(importService);
                }
            }
        }
    }

}
