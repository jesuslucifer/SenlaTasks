package controller;

import dao.ClientDAO;
import dao.RoomDAO;
import dao.ServiceDAO;
import lombok.extern.slf4j.Slf4j;
import model.IToCSV;
import model.Client;
import model.Hotel;
import model.Room;
import model.RoomStatus;
import model.Service;
import org.springframework.stereotype.Component;
import view.HotelView;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Component
@Slf4j
public class HotelController {
    private final Hotel hotel;
    private final HotelView view;
    private final RoomDAO roomDAO;
    private final ServiceDAO serviceDAO;
    private final ClientDAO clientDAO;


    public HotelController(Hotel hotel, HotelView view, RoomDAO roomDAO, ServiceDAO serviceDAO, ClientDAO clientDAO) {
        this.hotel = hotel;
        this.view = view;
        this.roomDAO = roomDAO;
        this.serviceDAO = serviceDAO;
        this.clientDAO = clientDAO;
    }

    public List<Room> getListFreeRooms() {
        return roomDAO.findByStatus(RoomStatus.FREE);
    }

    public List<Room> getRooms() {
        return roomDAO.findAll();
    }

    public List<Client> getClients() {
        return clientDAO.findAll();
    }

    public List<Service> getServices() {
        return serviceDAO.findAll();
    }

    public void printCountFreeRoom() {
        view.printCountFreeRoom(getListFreeRooms().size());
    }

    public void printCountClients() {
        view.printCountClients(getClients().size());
    }

    public void printRoomAndService(String typeSort) {
        if (typeSort.equals("Cost")) {
            List<Room> sortedRooms = new ArrayList<>(getListFreeRooms());
            List<Service> sortedServices = new ArrayList<>(getServices());
            sortedRooms.sort(Comparator.comparing(Room::getCost));
            sortedServices.sort(Comparator.comparing(Service::getCost));
            view.printRoomAndService(typeSort, sortedRooms, sortedServices);
        } else {
            view.printRoomAndService(typeSort, getRooms(), getServices());
        }
    }

    public <T extends IToCSV> void exportToCSV(List<T> list, String fileName) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.write(checkType(list));
            writer.println();
            for (T s : list) {
                writer.write(s.toCSV());
                writer.println();
            }
            view.printSuccessExport(fileName);
            log.info("Successfully exported to CSV file");
        } catch (FileNotFoundException | NullPointerException e) {
            log.error("Error exporting to CSV file", e);
        }
    }

    public <T> String checkType(List<T> list) {
        try {
            switch (list.getFirst().getClass().getSimpleName()) {
                case "Client" -> {
                    return "id,roomNumber,fullName,dateCheckIn,dateEvict,serviceId,serviceDate,...";
                }
                case "Room" -> {
                    return "id,roomNumber,cost,countStars,status,capacity,dateCheckIn,dateEvict";
                }
                case "Service" -> {
                    return "id,serviceName,cost";
                }
                default -> { }
            }
        } catch (NullPointerException | NoSuchElementException e) {
            log.error("Error checking type", e);
        }
        return null;
    }
}
