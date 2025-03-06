package controller;

import dao.ServiceDAO;
import lombok.extern.slf4j.Slf4j;
import model.Hotel;
import model.Service;
import org.springframework.stereotype.Component;
import view.ServiceView;

import java.io.File;
import java.util.Scanner;

@Component
@Slf4j
public class ServiceController {
    private final Hotel hotel;
    private final ServiceDAO serviceDAO;
    private final ServiceView view;

    public ServiceController(Hotel hotel, ServiceDAO serviceDAO, ServiceView view) {
        this.hotel = hotel;
        this.serviceDAO = serviceDAO;
        this.view = view;
    }

    public Service getService(String serviceName) {
        for (Service service : serviceDAO.findAll()) {
            if (service.getServiceName().equals(serviceName)) {
                return service;
            }
        }
        return null;
    }

    public void changeCostService(String serviceName, int cost) {
        serviceDAO.update(new Service(serviceName, cost));
    }

    public void printServices() {
        view.printServices(serviceDAO.findAll());
    }

    public void addService(String serviceName, int cost) {
        serviceDAO.create(new Service(serviceName, cost));
    }

    public boolean checkService(String service) {
        for (Service service1 : serviceDAO.findAll()) {
            if (service1.getServiceName().equals(service)) {
                log.info("Successfully checked service name");
                return true;
            }
        }
        return false;
    }

    public boolean servicesIsEmpty() {
        return serviceDAO.findAll().isEmpty();
    }

    public void importFromCSV(String fileName) {
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
                for (Service service : serviceDAO.findAll()) {
                    if (service.getId() == importService.getId()) {
                        service.updateFromCSV(split);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    serviceDAO.findAll().add(importService);
                }
            }
            System.out.println("Success import Services from services.csv");
            log.info("Success import Services from services.csv");
        } catch (Exception e) {
            log.error("Error import services from services.csv: ", e);
        }
    }
}
