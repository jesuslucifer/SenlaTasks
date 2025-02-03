package controller;

import dao.ClientDAO;
import dao.RoomDAO;
import model.Client;
import model.Hotel;
import model.Room;
import model.RoomStatus;
import view.RoomView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class RoomController {
    @Inject
    Hotel hotel;
    @Inject
    HotelController hotelController;
    @Inject
    RoomDAO roomDAO;
    @Inject
    ClientDAO clientDAO;
    @Inject
    RoomView view;

    public RoomController() {
    }

    public Room getRoom(int roomNumber) {
       return roomDAO.read(roomNumber);
    }

    public void checkIntoRoom(List<Client> buffClients, int roomNumber, LocalDate dateCheckIn, LocalDate dateEvict) {
       //getRoom(roomNumber).checkIntoRoom(buffClients, dateCheckIn, dateEvict, hotel.getClients());
       Room room = getRoom(roomNumber);
       if (room.isFree() && room.getCapacity() >= buffClients.size()) {
           room.setStatus(RoomStatus.BUSY);
           room.setDateCheckIn(dateCheckIn);
           room.setDateEvict(dateEvict);
           roomDAO.update(room);
           for (Client client : buffClients) {
               client.setRoomNumber(roomNumber);
               client.setDateCheckIn(dateCheckIn);
               client.setDateEvict(dateEvict);
               clientDAO.create(client);
           }
           System.out.println("The clients is accommodated in " + room.getRoomNumber() + " room");
       } else {
           System.out.println("This room is not available");
       }
    }

    public void evictFromRoom(int roomNumber) {
        Room room = getRoom(roomNumber);
        room.setStatus(RoomStatus.FREE);
        room.setDateCheckIn(null);
        room.setDateEvict(null);
        roomDAO.update(room);
        List<Client> clients = clientDAO.findInRoom(roomNumber);
        for (Client client : clients) {
            client.setOccupied(false);
            clientDAO.update(client);
        }
        System.out.println("The guest has been evicted from the " + roomNumber + " room");
    }

    public void changeStatusRoom(int roomNumber, RoomStatus status) {
        Room room = getRoom(roomNumber);
        if (!(room.isBusy() && status == RoomStatus.REPAIRED)) {
            room.setStatus(status);
            roomDAO.update(room);
        } else {
            System.out.println("The status of the room " + roomNumber + " cannot be changed, there are visitors in the room");
        }
    }

    public void changeLockedStatusRoom(boolean lockedStatus) {
        for (Room room : roomDAO.findAll()) {
            room.setLockedChangeStatus(lockedStatus);
            roomDAO.update(room);
        }
    }

    public void changeCostRoom(int roomNumber, int cost) {
        Room room = getRoom(roomNumber);
        room.setCost(cost);
        roomDAO.update(room);
    }

    public void printRooms(String typeSort, String typeRoom) {
        List<Room> list;

        list = switch (typeSort) {
            case "CapacityI" -> roomDAO.findWithSort(typeRoom, "capacity");
            case "CapacityD" -> roomDAO.findWithSort(typeRoom, "capacity DESC");
            case "CostI" -> roomDAO.findWithSort(typeRoom, "cost");
            case "CostD" -> roomDAO.findWithSort(typeRoom, "cost DESC");
            case "StarsI" -> roomDAO.findWithSort(typeRoom, "countStars");
            case "StarsD" -> roomDAO.findWithSort(typeRoom, "countStars DESC");
            default -> roomDAO.findWithSort(typeRoom, "roomNumber");
        };

        view.printRooms(list);
    }

    public void printInfoRoom(int roomNumber) {
        view.printInfoRoom(roomDAO.read(roomNumber), clientDAO.findInRoom(roomNumber));
    }

    public void printHistoryRoom(int roomNumber) {
        List<Client> clients = clientDAO.printHistory(roomNumber, getRoom(roomNumber).getCountRecordsHistory());
        view.printHistoryRoom(getRoom(roomNumber), clients);
    }

    public void printRoomFreeByDate(String date) {
        view.printRoomFreeByDate(formatDate(date), roomDAO.findFreeByDate(formatDate(date)));
    }

    public LocalDate formatDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(date, formatter);
    }

    public boolean checkRoom(int roomNumber) {
        return 0 < roomNumber && roomNumber <= roomDAO.findAll().size();
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
        for (Room room : roomDAO.findAll()) {
            room.setCountRecordsHistory(countRecordsHistory);
            roomDAO.update(room);
        }
    }

    public void importFromCSV(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] split = line.split(",");
                boolean found = false;
                for (Room room : roomDAO.findAll()) {
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
                    hotelController.getRooms().add(room);
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
            changeLockedStatusRoom(Boolean.parseBoolean(prop.getProperty("room.lockedChangeStatus")));
            System.out.println("Success import locked rooms from property, lockedChangeStatus=" + prop.getProperty("room.lockedChangeStatus"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void importCountRecordHistory() {
        try (FileInputStream fis = new FileInputStream("task-3/4/src/resources/config.property")) {
            Properties prop = new Properties();
            prop.load(fis);
            changeCountRecordsHistory(Integer.parseInt(prop.getProperty("room.countRecordsHistory")));
            System.out.println("Success import count records in the history room from property, countRecordsHistory=" + prop.getProperty("room.countRecordsHistory"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
