package model;

import controller.ConfigProperty;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class Service implements IToCSV, Serializable {
    @Serial
    private static final long serialVersionUID = 4L;
    private static int idInc;
    private final int id;

    @ConfigProperty(propertyName = "service.serviceName", type = String.class)
    private String serviceName;

    @ConfigProperty(propertyName = "service.cost", type = Integer.class)
    private int cost;

    private LocalDate serviceDate;

    public Service(String serviceName, int cost) {
        this.id = idInc++;
        this.serviceName = serviceName;
        this.cost = cost;
        this.serviceDate = LocalDate.of(2020, 1, 1);
    }

    public Service(int id, String serviceName, int cost, LocalDate serviceDate) {
        this.id = id;
        this.serviceName = serviceName;
        this.cost = cost;
        this.serviceDate = serviceDate;
    }

    public Service(int id, String serviceName, int cost) {
        this.id = id;
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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
        return String.valueOf(id) + ',' + serviceName + ',' + cost;
    }

    public void updateFromCSV(String[] csv) {
        setServiceName(csv[1]);
        setCost(Integer.parseInt(csv[2]));
    }
}
