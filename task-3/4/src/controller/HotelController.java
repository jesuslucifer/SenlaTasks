package controller;

import model.Client;
import model.Hotel;
import model.Room;
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

    public void printCountFreeRoom() {
        view.printCountFreeRoom(getListFreeRooms().size());
    }

    public void printCountClients() {
        view.printCountClients(getClients().size());
    }

    public void printRoomAndService(String typeSort) {
        List<Room> sortedRooms = getRooms();
        List<Service> sortedServices = getServices();
        sortedRooms.sort(Comparator.comparing(Room::getCost));
        sortedServices.sort(Comparator.comparing(Service::getCost));
        view.printRoomAndService(typeSort, getRooms(), getServices(), sortedServices, sortedRooms);
    }

}
