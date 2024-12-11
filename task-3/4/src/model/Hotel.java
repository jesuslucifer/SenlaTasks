package model;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Hotel {
    private final List<Room> rooms = new ArrayList<>();
    private final List<Service> services = new ArrayList<>();
    private final List<Client> clients = new ArrayList<>();

    public Hotel() {
        int COUNT_ROOMS = 11;
        for (int i = 1; i < COUNT_ROOMS; i++) {
            rooms.add(new Room(i));
        }
        System.out.println("The hotel is open, there are " + (COUNT_ROOMS - 1) + " rooms available");
    }

    public List<Room> getListFreeRooms() {
        List<Room> list = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isFree()) {
                list.add(room);
            }
        }
        return list;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Client> getClients() {
        return clients;
    }

    public List<Service> getServices() {
        return services;
    }

    public LocalDate formatDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(date, formatter);
    }

}
