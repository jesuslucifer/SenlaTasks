package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Client implements ToCSVImpl {
    private static int idInc = 0;
    private int id;
    private String passport;
    private String fullName;
    private int roomNumber;
    private LocalDate dateCheckIn;
    private LocalDate dateEvict;
    private final List<Service> services = new ArrayList<>();

    public Client(String passport, String fullName) {
        this.passport = passport;
        this.fullName = fullName;
    }

    public Client () {
        this.id = idInc++;
    }

    public int getId() {
        return id;
    }

    public String getPassport() {
        return passport;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassport(String passport) {
        this.passport = passport;
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
        services.add(service);
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
    }

    public LocalDate formatDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(date, formatter);
    }

    public String toCSV() {
        return String.valueOf(id) + ',' + roomNumber + ',' + fullName + ',' + dateCheckIn + ',' + dateEvict;
    }
}
