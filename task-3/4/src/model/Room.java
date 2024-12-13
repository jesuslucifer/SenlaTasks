package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class Room {
    private final int roomNumber;
    private int cost;
    private final int countStars;
    private RoomStatus status;
    private final int capacity;
    private final List<Client> clentList = new ArrayList<>();
    private LocalDate dateCheckIn;
    private LocalDate dateEvict;
    private final Deque<Client> historyClientQueue = new LinkedList<>();

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.countStars = new java.util.Random().nextInt(5) + 1;
        this.status = RoomStatus.FREE;
        this.capacity = new java.util.Random().nextInt(3) + 1;
        this.cost = (capacity + countStars) * 10;
        dateCheckIn = LocalDate.of(2020, 1, 1);
        dateEvict = LocalDate.of(2020, 1, 1);
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public int getCost(){
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public List<Client> getClentList() {
        return clentList;
    }

    public void setClientList(List<Client> clients) {
        clentList.addAll(clients);
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCountStars() {
        return countStars;
    }

    public void setDateCheckIn(LocalDate date) {
        dateCheckIn = date;
    }

    public LocalDate getDateCheckIn() {
        return dateCheckIn;
    }

    public void setDateEvict(LocalDate date) {
        dateEvict = date;
    }

    public LocalDate getDateEvict() {
        return dateEvict;
    }

    public void addClientToHistory(Client client) {
        historyClientQueue.add(client);
    }

    public Deque<Client> getHistoryClientQueue() {
        return historyClientQueue;
    }

    public boolean isFree() {
        return status == RoomStatus.FREE;
    }

    public boolean isBusy() {
        return status == RoomStatus.BUSY;
    }

    public boolean isRepaired() {
        return status == RoomStatus.REPAIRED;
    }

    public void checkIntoRoom(List<Client> buffClients, LocalDate dateCheckIn, LocalDate dateEvict, List<Client> clients) {
        boolean found = false;
        if (getCapacity() >= buffClients.size() && isFree()) {
            for (Client client : buffClients) {
                client.setRoomNumber(getRoomNumber());
                client.setDateCheckIn(dateCheckIn);
                client.setDateEvict(dateEvict);
            }
            setClientList(buffClients);
            setDateCheckIn(dateCheckIn);
            setDateEvict(dateEvict);
            clients.addAll(buffClients);
            found = true;
            setStatus(RoomStatus.BUSY);
            System.out.println("The clients is accommodated in " + getRoomNumber() + " room");
        }
        if (!found) {
            System.out.println("This room is not available");
        }
    }

    public void evictFromRoom(List<Client> clients) {
        for (Client client : getClentList()) {
            addClientToHistory(client);
        }
        setStatus(RoomStatus.FREE);
        setDateCheckIn(LocalDate.of(2020, 1, 1));
        setDateEvict(LocalDate.of(2020, 1, 1));
        clients.removeAll(getClentList());
        getClentList().clear();
        System.out.println("The guest has been evicted from the " + roomNumber + " room");
    }

    public void changeStatusRoom(RoomStatus status) {
        setStatus(status);
        System.out.println("The status of the room " + roomNumber + " has been changed to " + status);
    }

    public void changeCostRoom(int cost) {
        setCost(cost);
        System.out.println("The room cost has been changed to " + cost);
    }

    public LocalDate formatDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(date, formatter);
    }
}
