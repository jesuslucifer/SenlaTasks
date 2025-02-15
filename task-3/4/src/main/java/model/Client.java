package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Clients")
public class Client implements IToCSV, IUpdateFromCSV, Serializable {
    @Serial
    private static final long serialVersionUID = 2L;
    private static int idInc = 0;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //private String passport;
    @Column
    private String fullName;
    @Column
    private int roomNumber;
    @Column
    private LocalDate dateCheckIn;
    @Column
    private LocalDate dateEvict;
    @Transient
    private final List<Service> services = new ArrayList<>();
    @Column
    private Boolean occupied;

    public Client() {
        this.id = idInc++;
    }

    public Client(int id, int roomNumber, String fullName, LocalDate dateCheckIn, LocalDate dateEvict) {
        this.id = id;
        this.fullName = fullName;
        this.roomNumber = roomNumber;
        this.dateCheckIn = dateCheckIn;
        this.dateEvict = dateEvict;
    }

    public Client(int id, String fullName, int roomNumber, String dateCheckIn, String dateEvict) {
        this.id = id;
        this.fullName = fullName;
        this.roomNumber = roomNumber;
        this.dateCheckIn = LocalDate.parse(dateCheckIn);
        this.dateEvict = LocalDate.parse(dateEvict);
        occupied = true;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public Boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setDateCheckIn(LocalDate date) {
        dateCheckIn = date;
    }

    public void setDateCheckIn(String date) {
        dateCheckIn = formatDate(date);
    }

    public LocalDate getDateCheckIn() {
        return dateCheckIn;
    }

    public void setDateEvict(LocalDate date) {
        dateEvict = date;
    }

    public void setDateEvict(String date) {
        dateEvict = formatDate(date);
    }

    public LocalDate getDateEvict() {
        return dateEvict;
    }

    public void addService(Service service) {
        services.add(new Service(service.getId(), service.getServiceName(), service.getCost(), service.getServiceDate()));
    }

    public List<Service> getServices() {
        return services;
    }

    public void addServiceForClient(String serviceName, String serviceDate, List<Service> services) {
        for (Service service : services) {
            if (service.getServiceName().equals(serviceName)) {
                service.setServiceDate(formatDate(serviceDate));
                addService(service);
            }
        }
        System.out.println("Service: " + serviceName + ", Date: " + formatDate(serviceDate) + "added to client: " + getFullName());
    }

    public void addServiceForClient(int id, List<Service> services, LocalDate serviceDate) {
        for (Service service : services) {
            if (service.getId() == id) {
                service.setServiceDate(serviceDate);
                addService(service);
            }
        }
    }

    public LocalDate formatDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(date, formatter);
    }

    public String toCSV() {
        StringBuilder sr = new StringBuilder();
        for (Service service : services) {
            sr.append((service.getId())).append(',').append(service.getServiceDate()).append(',');
        }
        return String.valueOf(id) + ',' + roomNumber + ',' + fullName + ',' + dateCheckIn + ',' + dateEvict + ',' + sr;
    }


    public void updateFromCSV(String[] split) {
        setFullName(split[2]);
        setDateCheckIn(LocalDate.parse(split[3]));
        setDateEvict(LocalDate.parse(split[4]));
    }
}
