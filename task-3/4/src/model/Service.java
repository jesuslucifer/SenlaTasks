package model;

import java.time.LocalDate;

public class Service implements ToCSVImpl {
    private static int idInc;
    private final int id;
    private final String serviceName;
    private int cost;
    private LocalDate serviceDate;

    public Service(String serviceName, int cost) {
        this.id = idInc++;
        this.serviceName = serviceName;
        this.cost = cost;
        this.serviceDate = LocalDate.of(2020, 1, 1);
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public LocalDate getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(LocalDate serviceDate) {
        this.serviceDate = serviceDate;
    }

    public int getId() {
        return id;
    }

    public String toCSV() {
        return String.valueOf(id) + ',' + serviceName + ',' + cost + ',' + serviceDate;
    }
}
