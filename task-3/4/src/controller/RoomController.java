package controller;

import model.Client;
import model.Hotel;
import model.Room;
import model.RoomStatus;
import view.RoomView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;

public class RoomController {
    private final Hotel hotel;
    private final List<Room> rooms;
    private final RoomView view;

    public RoomController(Hotel hotel, RoomView view) {
        this.hotel = hotel;
        this.rooms = hotel.getRooms();
        this.view = view;
    }

    public Room getRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }

    public void checkIntoRoom(List<Client> buffClients, int roomNumber, LocalDate dateCheckIn, LocalDate dateEvict) {
       getRoom(roomNumber).checkIntoRoom(buffClients, dateCheckIn, dateEvict, hotel.getClients());
    }

    public void evictFromRoom(int roomNumber) {
        getRoom(roomNumber).evictFromRoom(hotel.getClients());
    }

    public void changeStatusRoom(int roomNumber, RoomStatus status) {
        getRoom(roomNumber).changeStatusRoom(status);
    }

    public void changeCostRoom(int roomNumber, int cost) {
        getRoom(roomNumber).changeCostRoom(cost);
    }

    public void printRooms(String typeSort, String typeRoom) {
        List<Room> list = rooms;

        if (typeRoom.equals("free"))
        {
            list = hotel.getListFreeRooms();
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
        view.printInfoRoom(getRoom(roomNumber));
    }

    public void printHistoryRoom(int roomNumber) {
        Deque<Client> deque = getRoom(roomNumber).getHistoryClientQueue();
        view.printHistoryRoom(getRoom(roomNumber), deque);
    }

    public void printRoomFreeByDate(String date) {
        List<Room> freeRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getDateEvict().isBefore(formatDate(date)) && !room.getStatus().equals(RoomStatus.REPAIRED)) {
                freeRooms.add(room);
            }
        }
        view.printRoomFreeByDate(formatDate(date), freeRooms);
    }

    public LocalDate formatDate(String date) {
        return hotel.formatDate(date);
    }
}
