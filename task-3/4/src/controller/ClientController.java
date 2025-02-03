package controller;

import dao.ClientDAO;
import dao.RoomDAO;
import dao.ServiceDAO;
import model.Client;
import model.Hotel;
import model.Room;
import model.Service;
import view.ClientView;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.DAYS;

public class ClientController {
    @Inject
    Hotel hotel;
    @Inject
    HotelController hotelController;
    @Inject
    ClientDAO clientDAO;
    @Inject
    ServiceDAO serviceDAO;
    @Inject
    RoomDAO roomDAO;
    @Inject
    ClientView view;

    public ClientController() {
    }

    public Client getClient(String fullName) {
        for (Client client : clientDAO.findAll()) {
            if (client.getFullName().equals(fullName)) {
                return client;
            }
        }
        return null;
    }

    public void addServiceForClient(String serviceName, int id, String serviceDate) {
        clientDAO.addService(clientDAO.read(id), serviceDAO.findServiceName(serviceName), formatDate(serviceDate));
    }

    public void printClients(String typeSort) {
        List<Client> list = List.of();

        switch (typeSort) {
            case "AlphabetA":
                 list = clientDAO.findAllWithSort("fullName ASC");
                break;
            case "AlphabetZ":
                list = clientDAO.findAllWithSort("fullName DESC");
                break;
            case "DateI":
                list = clientDAO.findAllWithSort("dateCheckIn ASC");
                break;
            case "DateD":
                list = clientDAO.findAllWithSort("dateCheckIn DESC");
                break;
            default:
                break;
        }

        view.printClients(list);
    }

    public void printClientServices(int id, String typeSort) {
        Client client = clientDAO.read(id);
        System.out.println("Services " + client.getFullName() + ":");
        List<Service> list = List.of();
        switch (typeSort) {
            case "CostI":
                list = clientDAO.getServices(client, "cost");
                break;
            case "CostD":
                list = clientDAO.getServices(client, "cost DESC");
                break;
            case "DateI":
                list = clientDAO.getServices(client, "serviceDate");
                break;
            case "DateD":
                list = clientDAO.getServices(client, "serviceDate DESC");
                break;
            default:
                break;
        }
        view.printClientServices(list);
    }

    public void printCostPerRoom(int id) {
        try {
            Client client = clientDAO.read(id);
            long daysBetween = DAYS.between(client.getDateCheckIn(), client.getDateEvict());
            Room room = roomDAO.read(client.getRoomNumber());
            long cost = daysBetween * room.getCost();
            view.printCostPerRoom(cost);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public boolean checkDate(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate.parse(date, formatter);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean checkService(String service) {
        for (Service service1 : serviceDAO.findAll()) {
            if (service1.getServiceName().equals(service)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkFullName(String fullName) {
        for (Client client : clientDAO.findAll()) {
            if (client.getFullName().equals(fullName)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkID(int id) {
        for (Client client : clientDAO.findAll()) {
            if (client.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean clientsIsEmpty() {
        return clientDAO.findAll().isEmpty();
    }

    public void importFromCSV(String fileName){
        try (Scanner scanner = new Scanner(new File(fileName))) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] split = line.split(",");

                Client importClient = new Client(
                        Integer.parseInt(split[0]), //id
                        Integer.parseInt(split[1]), //roomNumber
                        split[2],                   //fullName
                        LocalDate.parse(split[3]),  //checkIn
                        LocalDate.parse(split[4])); //evict

                boolean found = false;
                for (Client client : clientDAO.findAll()) {
                    if (client.getId() == importClient.getId()) {
                        client = addServiceToClient(split, client);
                        client.updateFromCSV(split);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    importClient = addServiceToClient(split, importClient);
                    clientDAO.findAll().add(importClient);
                    for (Room room : hotelController.getRooms()) {
                        if (room.getRoomNumber() == importClient.getRoomNumber()) {
                            room.checkIntoRoom(importClient, hotelController.getClients());
                            break;
                        }
                    }
                }
            }
            System.out.println("Success import Clients from clients.csv");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public LocalDate formatDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(date, formatter);
    }

    public Client addServiceToClient(String[] split, Client client) {
        if (split.length > 5) {
            for (int i = 0; i < split.length - 5; i += 2) {
                client.addServiceForClient(Integer.parseInt(split[i + 5]), hotelController.getServices(), formatDate(split[i + 6]));
            }
        }
        return client;
    }
}
