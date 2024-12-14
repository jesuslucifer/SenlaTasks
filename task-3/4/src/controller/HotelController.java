package controller;

import model.ToCSVImpl;
import model.Client;
import model.Hotel;
import model.Room;
import model.Service;
import view.HotelView;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
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
        if (typeSort.equals("Cost")) {
            List<Room> sortedRooms = new ArrayList<>(getListFreeRooms());
            List<Service> sortedServices = new ArrayList<>(getServices());
            sortedRooms.sort(Comparator.comparing(Room::getCost));
            sortedServices.sort(Comparator.comparing(Service::getCost));
            view.printRoomAndService(typeSort, sortedRooms,sortedServices);
        }
        else {
            view.printRoomAndService(typeSort, getRooms(), getServices());
        }

    }

    public <T extends ToCSVImpl> void exportToCSV(List<T> list, String fileName) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            for (T s : list) {
                writer.write(s.toCSV());
                writer.println();
            }
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
