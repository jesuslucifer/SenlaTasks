package controller;

import dao.ServiceDAO;
import model.Hotel;
import model.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import view.ServiceView;

import java.io.File;
import java.util.Scanner;

public class ServiceController {
    @Inject
    Hotel hotel;
    @Inject
    ServiceDAO serviceDAO;
    @Inject
    ServiceView view;
    private static final Logger logger = LoggerFactory.getLogger(ServiceController.class);

    public ServiceController() {
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
                logger.info("Successfully checked service name");
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
            logger.info("Success import Services from services.csv");
        } catch (Exception e) {
            logger.error("Error import services from services.csv: ", e);
        }
    }
}
