package controller;

import model.Client;
import model.Hotel;
import model.Room;
import model.RoomStatus;
import model.Service;
import view.HotelView;

import java.time.LocalDate;
import java.util.List;

public class HotelController {
    Hotel hotel;
    HotelView view;

    public HotelController(Hotel hotel, HotelView view) {
        this.hotel = hotel;
        this.view = view;
    }

    public void checkIntoRoom(List<Client> buffClients, String dateCheckIn, String dateEvict) {
        hotel.checkIntoRoom(buffClients, dateCheckIn, dateEvict);
    }

    public void evictFromRoom(int numberRoom) {
        hotel.evictFromRoom(numberRoom);
    }

    public void changeStatusRoom(int roomNumber, RoomStatus status) {
        hotel.changeStatusRoom(roomNumber, status);
    }

    public void changeCostRoom(int roomNumber, int cost) {
        hotel.changeCostRoom(roomNumber, cost);
    }

    public void addService(String serviceName, int cost) {
        hotel.addService(serviceName, cost);
    }

    public void changeCostService(String serviceName, int cost) {
        hotel.changeCostService(serviceName, cost);
    }

    public void addServiceForClient(String serviceName, String fullName, String serviceDate) {
        hotel.addServiceForClient(serviceName, fullName, serviceDate);
    }

    public List<Room> getListFreeRooms() {
        return hotel.getListFreeRooms();
    }

    public List<Room> getRooms() {
        return hotel.getRooms();
    }

    public List<Client> getClients() {
        return hotel.getClients();
    }

    public List<Service> getServices() {
        return hotel.getServices();
    }

    public LocalDate formatDate(String date) {
        return hotel.formatDate(date);
    }

    public void printRooms(String typeSort, String typeRoom) {
        view.printRooms(typeSort, typeRoom, getRooms(), getListFreeRooms());
    }

    public void printInfoRoom(int roomNumber) {
        view.printInfoRoom(roomNumber, getRooms());
    }

    public void printCountFreeRoom() {
        view.printCountFreeRoom(getListFreeRooms());
    }

    public void printCostPerRoom(String fullName) {
        view.printCostPerRoom(fullName, getClients(), getRooms());
    }

    public void printHistoryRoom(int roomNumber) {
        view.printHistoryRoom(roomNumber, getRooms());
    }

    public void printRoomFreeByDate(String date) {
        view.printRoomFreeByDate(formatDate(date), getRooms());
    }

    public void printCountClients() {
        view.printCountClients(getClients());
    }

    public void printClientList(String typeSort) {
        view.printClientList(typeSort, getClients());
    }

    public void printClientServices(String fullName, String typeSort) {
        view.printClientServices(fullName, typeSort, getClients());
    }

    public void printRoomAndService(String typeSort) {
        view.printRoomAndService(typeSort, getRooms(), getServices());
    }

}
