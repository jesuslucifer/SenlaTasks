package controller;

import model.Client;
import model.Hotel;
import model.Room;
import model.Service;
import view.ClientView;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.DAYS;

public class ClientController {
    private final Hotel hotel;
    private final List<Client> clients;
    private final ClientView view;


    public ClientController(Hotel hotel, ClientView view) {
        this.hotel = hotel;
        this.clients = hotel.getClients();
        this.view = view;
    }

    public Client getClient(String fullName) {
        for (Client client : clients) {
            if (client.getFullName().equals(fullName)) {
                return client;
            }
        }
        return null;
    }

    public void addServiceForClient(String serviceName, String fullName, String serviceDate) {
        getClient(fullName).addServiceForClient(serviceName, serviceDate, hotel.getServices());
    }

    public void printClients(String typeSort) {
        List<Client> list = clients;

        switch (typeSort) {
            case "AlphabetA":
                list.sort(Comparator.comparing(Client::getFullName));
                break;
            case "AlphabetZ":
                list.sort(Comparator.comparing(Client::getFullName).reversed());
                break;
            case "DateI":
                list.sort(Comparator.comparing(Client::getDateEvict));
                break;
            case "DateD":
                list.sort(Comparator.comparing(Client::getDateEvict).reversed());
                break;
            default:
                break;
        }

        view.printClients(list);
    }

    public void printClientServices(String fullName, String typeSort) {
        System.out.println("Services " + getClient(fullName).getFullName() + ":");
        List<Service> list = getClient(fullName).getServices();
        switch (typeSort) {
            case "CostI":
                list.sort(Comparator.comparing(Service::getCost));
                break;
            case "CostD":
                list.sort(Comparator.comparing(Service::getCost).reversed());
                break;
            case "DateI":
                list.sort(Comparator.comparing(Service::getServiceDate));
                break;
            case "DateD":
                list.sort(Comparator.comparing(Service::getServiceDate).reversed());
                break;
            default:
                break;
        }
        view.printClientServices(list);
    }

    public void printCostPerRoom(String fullName) {
        for (Client client : hotel.getClients()) {
            if (client.getFullName().equals(fullName)) {
                long daysBetween = DAYS.between(client.getDateCheckIn(), client.getDateEvict());
                for (Room room : hotel.getRooms()) {
                    if (room.getRoomNumber() == client.getRoomNumber()) {
                        long cost = daysBetween * room.getCost();
                        view.printCostPerRoom(cost);
                    }
                }
            }
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
        for (Service service1 : hotel.getServices()) {
            if (service1.getServiceName().equals(service)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkFullName(String fullName) {
        for (Client client : clients) {
            if (client.getFullName().equals(fullName)) {
                return true;
            }
        }
        return false;
    }

    public boolean clientsIsEmpty() {
        return clients.isEmpty();
    }

    public void importFromCSV(String fileName) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(fileName))) {
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
                for (Client client : clients) {
                    if (client.getId() == importClient.getId()) {
                        client = addServiceToClient(split, client);
                        client.updateFromCSV(split);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    importClient = addServiceToClient(split, importClient);
                    clients.add(importClient);
                    for (Room room : hotel.getRooms()) {
                        if (room.getRoomNumber() == importClient.getRoomNumber()) {
                            room.checkIntoRoom(importClient, hotel.getClients());
                            break;
                        }
                    }
                }
            }
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
                client.addServiceForClient(Integer.parseInt(split[i + 5]), hotel.getServices(), formatDate(split[i + 6]));
            }
        }
        return client;
    }
}
