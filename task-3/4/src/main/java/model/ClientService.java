package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "ClientService")
public class ClientService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "serviceId")
    private Service service;

    @Column(name = "serviceDate")
    private LocalDate serviceDate;

    public ClientService() {

    }

    public ClientService(Client client, Service service, LocalDate serviceDate) {
        this.client = client;
        this.service = service;
        this.serviceDate = serviceDate;
    }

    public Service getService() {
        return service;
    }

    public Client getClient() {
        return client;
    }

    public LocalDate getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(LocalDate serviceDate) {
        this.serviceDate = serviceDate;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
