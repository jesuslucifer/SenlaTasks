package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "Rooms")
public class Room implements IToCSV, IUpdateFromCSV {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int roomNumber;
    @Column
    private int cost;
    @Column
    private int countStars;

    @Column
    @Value("${room.lockedChangeStatus}")
    private boolean lockedChangeStatus;

    @Column
    @Enumerated(EnumType.STRING)
    private RoomStatus status;
    @Column
    private int capacity;

    @Transient
    private final List<Client> clentList = new ArrayList<>();
    @Column
    private LocalDate dateCheckIn;
    @Column
    private LocalDate dateEvict;
    @Transient
    private final Deque<Client> historyClientQueue = new LinkedList<>();

    @Column
    @Value("${room.countRecordHistory}")
    private int countRecordsHistory;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.countStars = new java.util.Random().nextInt(5) + 1;
        this.status = RoomStatus.FREE;
        this.capacity = new java.util.Random().nextInt(3) + 1;
        this.cost = (capacity + countStars) * 10;
        dateCheckIn = null;
        dateEvict = null;
        lockedChangeStatus = true;
        countRecordsHistory = 3;
    }

    public Room(int id, int roomNumber, int cost, int countStars, RoomStatus status, int capacity, LocalDate dateCheckIn, LocalDate dateEvict) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.cost = cost;
        this.countStars = countStars;
        this.status = status;
        this.capacity = capacity;
        this.dateCheckIn = dateCheckIn;
        this.dateEvict = dateEvict;
        lockedChangeStatus = true;
        countRecordsHistory = 3;
    }

    public Room(int id, int roomNumber, int cost, int countStars, String status, int capacity, Boolean lockedChangeStatus, int countRecordsHistory) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.cost = cost;
        this.countStars = countStars;
        this.status = stringToRoomStatus(status);
        this.capacity = capacity;
        this.lockedChangeStatus = lockedChangeStatus;
        this.countRecordsHistory = countRecordsHistory;
    }

    public Room() {

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

    public int getCost() {
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

    public void setClient(Client client) {
        clentList.add(client);
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCountStars() {
        return countStars;
    }

    public void setCountStars(int countStars) {
        this.countStars = countStars;
    }

    public void setDateCheckIn(LocalDate date) {
        dateCheckIn = date;
    }

    public LocalDate getDateCheckIn() {
        return dateCheckIn;
    }

    public RoomStatus stringToRoomStatus(String status) {
        return RoomStatus.valueOf(status.toUpperCase());
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

    public void checkIntoRoom(Client client, List<Client> clients) {
        if (getCapacity() - getClentList().size() < 1) {
            evictFromRoom(clients);
        }
        setClient(client);
        setStatus(RoomStatus.BUSY);
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
        if (lockedChangeStatus) {
            if (!isBusy()) {
                setStatus(status);
                System.out.println("The status of the room " + roomNumber + " has been changed to " + status);
            } else {
                System.out.println("The status of the room " + roomNumber + " cannot be changed, there are visitors in the room");
            }
        } else {
            System.out.println("The status of the room " + roomNumber + " cannot be changed");
        }
    }

    public void changeCostRoom(int cost) {
        setCost(cost);
        System.out.println("The room cost has been changed to " + cost);
    }

    public LocalDate formatDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(date, formatter);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLockedChangeStatus(boolean lockedChangeStatus) {
        this.lockedChangeStatus = lockedChangeStatus;
    }

    public boolean getLockedChangeStatus() {
        return lockedChangeStatus;
    }

    @Override
    public String toCSV() {
        return String.valueOf(id) + ',' + roomNumber + ',' + cost + ',' + countStars + ',' + status + ',' + capacity
                + ',' + dateCheckIn + ',' + dateEvict;
    }

    @Override
    public void updateFromCSV(String[] csv) {
        setCost(Integer.parseInt(csv[2]));
        setCountStars(Integer.parseInt(csv[3]));
        setStatus(RoomStatus.valueOf(csv[4]));
        setCapacity(Integer.parseInt(csv[5]));
        setDateCheckIn(LocalDate.parse(csv[6]));
        setDateEvict(LocalDate.parse(csv[7]));
    }

    public int getCountRecordsHistory() {
        return countRecordsHistory;
    }

    public void setCountRecordsHistory(int countRecordsHistory) {
        this.countRecordsHistory = countRecordsHistory;
    }
}
