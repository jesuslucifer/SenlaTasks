package controller;

import model.IExitProgram;
import model.IToCSV;
import model.Client;
import model.Hotel;
import model.Room;
import model.Service;
import view.HotelView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class HotelController implements IExitProgram {
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

    public <T extends IToCSV> void exportToCSV(List<T> list, String fileName) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.write(checkType(list));
            writer.println();
            for (T s : list) {
                writer.write(s.toCSV());
                writer.println();
            }
            view.printSuccessExport(fileName);
        }
        catch (FileNotFoundException | NullPointerException e) {
            System.err.println(e.getMessage());
        }
    }

    public <T> String checkType(List<T> list) {
        try {
            switch (list.getFirst().getClass().getSimpleName()) {
                case "Client" -> {
                    return "id,roomNumber,fullName,dateCheckIn,dateEvict,serviceId,serviceDate,...";
                }
                case "Room" -> {
                    return "id,roomNumber,cost,countStars,status,capacity,dateCheckIn,dateEvict";
                }
                case "Service" -> {
                    return "id,serviceName,cost";
                }
                default -> {}
            }
        } catch (NullPointerException | NoSuchElementException e) {
            System.err.println("List is empty");
        }
        return null;
    }

    public void exit() {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("task-3/4/src/resources/save.dat"))) {
            oos.writeObject(hotel);
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
