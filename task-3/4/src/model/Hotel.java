package model;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Hotel {
    private List<Room> rooms = new ArrayList<>();
    private List<Service> services = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();
    private final int COUNT_ROOMS = 10;

    public Hotel() {
        for (int i = 0; i < COUNT_ROOMS; i++) {
            rooms.add(new Room(i));
        }
        System.out.println("The hotel is open, there are " + COUNT_ROOMS + " rooms available");
    }

    //Заселяет клиентов в номер
    public void checkIntoRoom(List<Client> buffClients, String dateCheckIn, String dateEvict) {
        boolean found = false;
        for (Room room : rooms) {
            if (room.getCapacity() >= buffClients.size() && room.isFree()) {
                for (Client client : buffClients) {
                    client.setRoomNumber(room.getRoomNumber());
                    client.setDateCheckIn(formatDate(dateCheckIn));
                    client.setDateEvict(formatDate(dateEvict));
                }
                room.setClientList(buffClients);
                room.setDateCheckIn(formatDate(dateCheckIn));
                room.setDateEvict(formatDate(dateEvict));
                clients.addAll(buffClients);
                found = true;
                room.setStatus(RoomStatus.BUSY);
                System.out.println("The clients is accommodated in " + room.getRoomNumber() + " room");
                break;
            }
        }
        if (!found) {
            System.out.println("There are no places in the hotel");
        }
    }

    //Выселяет клиентов из номера
    public void evictFromRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                for (Client client : room.getClentList()) {
                    room.addClientToHistory(client);
                }
                room.setStatus(RoomStatus.FREE);
                room.setDateCheckIn(LocalDate.of(2020, 1, 1));
                room.setDateEvict(LocalDate.of(2020, 1, 1));
                clients.removeAll(room.getClentList());
                room.getClentList().clear();
            }
        }
        System.out.println("The guest has been evicted from the " + roomNumber + " room");
    }

    //Меняет статус номера
    public void changeStatusRoom(int roomNumber, RoomStatus status) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                room.setStatus(status);
            }
        }
        System.out.println("The status of the room " + roomNumber + " has been changed to " + status);
    }

    //Меняет стоимость номера
    public void changeCostRoom(int roomNumber, int cost) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                room.setCost(cost);
            }
        }
        System.out.println("The room cost has been changed to " + cost);
    }

    //Меняет стоимость услуги
    public void changeCostService(String serviceName, int cost) {
        for (Service service : services) {
            if (service.getServiceName().equals(serviceName)) {
                service.setCost(cost);
            }
        }
        System.out.println("The service cost " + serviceName + " has been changed to " + cost);
    }

    //Добавляет услугу
    public void addService(String serviceName, int cost) {
        services.add(new Service(serviceName, cost));
        System.out.println("The service " + serviceName + " has been added to the hotel");
    }

    //Добавляет услугу клиенту
    public void addServiceForClient(String serviceName, String fullName, String serviceDate) {
        for (Client client : clients) {
            if (client.getFullName().equals(fullName)) {
                for (Service service : services) {
                    if (service.getServiceName().equals(serviceName)) {
                        service.setServiceDate(formatDate(serviceDate));
                        client.addService(service);
                    }
                }
            }
        }
    }

    //Возвращает список свободныхномеров
    public List<Room> getListFreeRooms() {
        List<Room> list = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isFree()) {
                list.add(room);
            }
        }
        return list;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Client> getClients() {
        return clients;
    }

    public List<Service> getServices() {
        return services;
    }

    //Форматирует дату из String в LocalDate
    public LocalDate formatDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(date, formatter);
    }

}
