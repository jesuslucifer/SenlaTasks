package controller;

import model.Client;
import model.Hotel;
import model.IExitProgram;
import model.Room;
import model.RoomStatus;
import view.RoomView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class RoomController implements IExitProgram {
    private final Hotel hotel;
    private final List<Room> rooms;
    private final RoomView view;

    public RoomController(Hotel hotel, RoomView view) {
        this.hotel = hotel;
        this.rooms = hotel.getRooms();
        this.view = view;
        importLockedRoomProperty();
        importCountRecordHistory();
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

    public void changeLockedStatusRoom(boolean lockedStatus) {
        rooms.forEach(room -> room.setLockedChangeStatus(lockedStatus));
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
        Deque<Client> deque = new LinkedList<>(getRoom(roomNumber).getHistoryClientQueue());
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

    public boolean checkRoom(int roomNumber) {
        return 0 < roomNumber && roomNumber <= rooms.size();
    }

    public boolean checkCapacityRoom(int roomNumber, int countClients) {
        return getRoom(roomNumber).getCapacity() >= countClients;
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

    public void changeCountRecordsHistory(int countRecordsHistory) {
        rooms.forEach(room -> room.setCountRecordsHistory(countRecordsHistory));
    }

    public void importFromCSV(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] split = line.split(",");
                boolean found = false;
                for (Room room : rooms) {
                    if (room.getId() == Integer.parseInt(split[0])) {
                        room.updateFromCSV(split);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    Room room = new Room(
                            Integer.parseInt(split[0]),
                            Integer.parseInt(split[1]),
                            Integer.parseInt(split[2]),
                            Integer.parseInt(split[3]),
                            RoomStatus.valueOf(split[4]),
                            Integer.parseInt(split[5]),
                            LocalDate.parse(split[6]),
                            LocalDate.parse(split[7]));
                    hotel.getRooms().add(room);
                }
            }
            System.out.println("Success import Rooms from rooms.csv");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void importLockedRoomProperty() {
        try (FileInputStream fis = new FileInputStream("task-3/4/src/resources/config.property")) {
            Properties prop = new Properties();
            prop.load(fis);
            changeLockedStatusRoom(Boolean.parseBoolean(prop.getProperty("lockedChangeStatus")));
            System.out.println("Success import locked rooms from property, lockedChangeStatus=" + prop.getProperty("lockedChangeStatus"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void importCountRecordHistory() {
        try (FileInputStream fis = new FileInputStream("task-3/4/src/resources/config.property")) {
            Properties prop = new Properties();
            prop.load(fis);
            changeCountRecordsHistory(Integer.parseInt(prop.getProperty("countRecordsHistory")));
            System.out.println("Success import count records in the history room from property, countRecordsHistory=" + prop.getProperty("countRecordsHistory"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
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
