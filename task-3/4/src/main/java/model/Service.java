package model;

import controller.ConfigProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.time.LocalDate;

@Entity
@Table(name = "Services")
public class Service implements IToCSV {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ConfigProperty(propertyName = "service.serviceName", type = String.class)
    @Column(name = "serviceName")
    private String serviceName;

    @ConfigProperty(propertyName = "service.cost", type = Integer.class)
    @Column(name = "cost")
    private int cost;

    @Transient
    private LocalDate serviceDate;

    public Service(String serviceName, int cost) {
        this.serviceName = serviceName;
        this.cost = cost;
        this.serviceDate = null;
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

    public Service() {

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
