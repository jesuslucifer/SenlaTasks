package controller;

import model.Client;
import model.Hotel;
import model.Room;
import model.RoomStatus;
import model.Service;
import view.HotelView;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class HotelController {
    Hotel hotel;
    HotelView view;

    public HotelController(Hotel hotel, HotelView view) {
        this.hotel = hotel;
        this.view = view;
    }

    public void checkIntoRoom(List<Client> buffClients, String dateCheckIn, String dateEvict) {
        hotel.checkIntoRoom(buffClients, dateCheckIn, dateEvict);
    }

    public void evictFromRoom(int numberRoom) {
        hotel.evictFromRoom(numberRoom);
    }

    public void changeStatusRoom(int roomNumber, RoomStatus status) {
        hotel.changeStatusRoom(roomNumber, status);
    }

    public void changeCostRoom(int roomNumber, int cost) {
        hotel.changeCostRoom(roomNumber, cost);
    }

    public void addService(String serviceName, int cost) {
        hotel.addService(serviceName, cost);
    }

    public void changeCostService(String serviceName, int cost) {
        hotel.changeCostService(serviceName, cost);
    }

    public void addServiceForClient(String serviceName, String fullName, String serviceDate) {
        hotel.addServiceForClient(serviceName, fullName, serviceDate);
    }

    public List<Room> getListFreeRooms() {
        return hotel.getListFreeRooms();
    }

    public List<Room> getRooms() {
        return hotel.getRooms();
    }

    public List<Client> getClients() {
        return hotel.getClients();
    }

    public List<Service> getServices() {
        return hotel.getServices();
    }

    public LocalDate formatDate(String date) {
        return hotel.formatDate(date);
    }

    public void printRooms(String typeSort, String typeRoom) {
        List<Room> list = getRooms();

        if (typeRoom.equals("free"))
        {
            list = getListFreeRooms();
        }

        switch (typeSort) {
            case "CapacityI":
                list.sort(Comparator.comparing(Room::getCapacity));
                break;
            case "CapacityD":
                list.sort(Comparator.comparing(Room::getCapacity).reversed());
                break;
            case "CostI":
                list.sort(Comparator.comparing(Room::getCost));
                break;
            case "CostD":
                list.sort(Comparator.comparing(Room::getCost).reversed());
                break;
            case "StarsI":
                list.sort(Comparator.comparing(Room::getCountStars));
                break;
            case "StarsD":
                list.sort(Comparator.comparing(Room::getCountStars).reversed());
                break;
            default:
                list.sort(Comparator.comparing(Room::getRoomNumber));
                break;
        }

        view.printRooms(list);
    }

    public void printInfoRoom(int roomNumber) {
        view.printInfoRoom(roomNumber, getRooms());
    }

    public void printCountFreeRoom() {
        view.printCountFreeRoom(getListFreeRooms());
    }

    public void printCostPerRoom(String fullName) {
        view.printCostPerRoom(fullName, getClients(), getRooms());
    }

    public void printHistoryRoom(int roomNumber) {
        view.printHistoryRoom(roomNumber, getRooms());
    }

    public void printRoomFreeByDate(String date) {
        view.printRoomFreeByDate(formatDate(date), getRooms());
    }

    public void printCountClients() {
        view.printCountClients(getClients());
    }

    public void printClientList(String typeSort) {
        List<Client> list = getClients();

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

        view.printClientList(getClients());
    }

    public void printClientServices(String fullName, String typeSort) {
        for (Client client : getClients()) {
            if (client.getFullName().equals(fullName)) {
                System.out.println("Services " + client.getFullName() + ":");
                List<Service> list = client.getServices();
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
                break;
            }
        }
    }

    public void printRoomAndService(String typeSort) {
        view.printRoomAndService(typeSort, getRooms(), getServices());
    }

}
