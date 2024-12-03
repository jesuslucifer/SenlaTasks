import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Deque;
import static java.time.temporal.ChronoUnit.DAYS;

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
            if (room.getCapacity() >= buffClients.size() && room.getStatus().equals("free")) {
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
                room.setStatus("busy");
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
                room.setStatus("free");
                room.setDateCheckIn(LocalDate.of(2020, 1, 1));
                room.setDateEvict(LocalDate.of(2020, 1, 1));
                clients.removeAll(room.getClentList());
                room.getClentList().clear();
            }
        }
        System.out.println("The guest has been evicted from the " + roomNumber + " room");
    }

    //Меняет статус номера
    public void changeStatusRoom(int roomNumber, String status) {
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

    //Добавляет услугу
    public void addService(String serviceName, int cost) {
        services.add(new Service(serviceName, cost));
        System.out.println("The service " + serviceName + " has been added to the hotel");
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

    //Выводит отсортированный список номеров
    public void printRooms(String typeSort, String typeRoom){
        List<Room> list = rooms;

        if (typeRoom.equals("free"))
        {
            list = getListFreeRooms();
        }

        switch (typeSort) {
            case "CapacityI":
                list.sort(Comparator.comparing(Room::getCapacity));
                break;
            case "CapacityD":
                list.sort(Comparator.comparing(Room::getCapacity).reversed());
                break;
            case "CostI":
                list.sort(Comparator.comparing(Room::getCost));
                break;
            case "CostD":
                list.sort(Comparator.comparing(Room::getCost).reversed());
                break;
            case "StarsI":
                list.sort(Comparator.comparing(Room::getCountStars));
                break;
            case "StarsD":
                list.sort(Comparator.comparing(Room::getCountStars).reversed());
                break;
            default:
                list.sort(Comparator.comparing(Room::getRoomNumber));
                break;
        }
        for (Room room : list) {
            System.out.println("Room: " + room.getRoomNumber() + " Status: " + room.getStatus() + " Stars: " + room.getCountStars() + " Capacity: " + room.getCapacity() + " Cost: " + room.getCost());
        }
    }

    //Возвращает список свободныхномеров
    public List<Room> getListFreeRooms() {
        List<Room> list = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getStatus().equals("free")) {
                list.add(room);
            }
        }
        return list;
    }

    //Выводит количество клиентов в отеле
    public void printCountClients() {
        System.out.println("Count clients: " + clients.size());
    }

    //Выводит кол-во свободных номеров
    public void printCountFreeRoom() {
        System.out.println("Count free rooms: " +  getListFreeRooms().size());
    }

    //Выводит информацию о номере
    public void printInfoRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                if (room.getStatus().equals("busy")) {
                    System.out.println("Room: " + room.getRoomNumber() + " Status: " + room.getStatus() + " Stars: " + room.getCountStars() + " Capacity: " + room.getCapacity() + " Cost: " + room.getCost());
                    System.out.print("Clients: ");
                    for (Client client : room.getClentList()) {
                        System.out.print(client.getFullName() + " ");
                    }
                    System.out.println();
                } else {
                    System.out.println("Room: " + room.getRoomNumber() + " Status: " + room.getStatus() + " Stars: " + room.getCountStars() + " Capacity: " + room.getCapacity() + " Cost: " + room.getCost());
                }
            }
        }
    }

    //Форматирует дату из String в LocalDate
    public LocalDate formatDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(date, formatter);
    }

    //Выводит отсортированный список клиентов
    public void printClientList(String typeSort) {
        List<Client> list = clients;

        switch (typeSort) {
            case "AlphabetA":
                list.sort(Comparator.comparing(Client::getFullName));
                break;
            case "AlphabetZ":
                list.sort(Comparator.comparing(Client::getFullName).reversed());
                break;
            case "DateI":
                list.sort(Comparator.comparing(Client::getDateEvict));
                break;
            case "DateD":
                list.sort(Comparator.comparing(Client::getDateEvict).reversed());
                break;
            default:
                break;
        }
        for (Client client : list) {
            System.out.println(client.getFullName() + " " + client.getDateEvict() + " Room: " + client.getRoomNumber());
        }
    }

    //Выводит итоговую стоимость номера
    public void printCostPerRoom(String fullName) {
        for (Client client : clients) {
            if (client.getFullName().equals(fullName)) {
                long daysBetween = DAYS.between(client.getDateCheckIn(), client.getDateEvict());
                for (Room room : rooms) {
                    if (room.getRoomNumber() == client.getRoomNumber()) {
                        System.out.println("Cost per room: " + daysBetween * room.getCost());
                    }
                }
            }
        }
    }

    //Выводит отсортированный список номеров и услуг в них
    public void printRoomAndService(String typeSort) {
        switch (typeSort) {
            case "ChapterR":
                for (Room room : rooms) {
                    System.out.println("Room: " + room.getRoomNumber() + " Cost: " + room.getCost());
                }
                for (Service service : services) {
                    System.out.println("Service: " + service.getServiceName() + " Cost: " + service.getCost());
                }
                break;
            case "ChapterS":
                for (Service service : services) {
                    System.out.println("Service: " + service.getServiceName() + " Cost: " + service.getCost());
                }
                for (Room room : rooms) {
                    System.out.println("Room: " + room.getRoomNumber() + " Cost: " + room.getCost());
                }
                break;
            case "Cost":
                List<Room> list = rooms;
                List<Service> list2 = services;
                list.sort(Comparator.comparing(Room::getCost));
                list2.sort(Comparator.comparing(Service::getCost));
                for (Service service : list2) {
                    System.out.println("Service: " + service.getServiceName() + " Cost: " + service.getCost());
                }
                for (Room room : list) {
                    System.out.println("Room: " + room.getRoomNumber() + " Cost: " + room.getCost());
                }
                break;
        }
    }

    //Выводит список свободных номеров которые будут доступны после определенной даты
    public void printRoomFreeByDate(String date) {
        System.out.println("Free rooms by " + date);
        for (Room room : rooms) {
            if (room.getDateEvict().isBefore(formatDate(date))) {
                System.out.println("Room: " + room.getRoomNumber());
            }
        }
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

    //Выводит отсортированные списки клиентов и услуг
    public void printClientServices(String fullName, String typeSort) {
        for (Client client : clients) {
            if (client.getFullName().equals(fullName)) {
                System.out.println("Services " + client.getFullName() + ":");
                List<Service> list = client.getServices();
                switch (typeSort) {
                    case "CostI":
                        list.sort(Comparator.comparing(Service::getCost));
                        break;
                    case "CostD":
                        list.sort(Comparator.comparing(Service::getCost).reversed());
                        break;
                    case "DateI":
                        list.sort(Comparator.comparing(Service::getServiceDate));
                        break;
                    case "DateD":
                        list.sort(Comparator.comparing(Service::getServiceDate).reversed());
                        break;
                    default:
                        break;
                }
                for (Service service : list) {
                    System.out.println(service.getServiceName() + " Cost: " + service.getCost() + " Date: " + service.getServiceDate());
                }
                break;
            }
        }
    }

    //Выводит 3-х последних постояльцев номера
    public void printHistoryRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                System.out.println("Room " + room.getRoomNumber() + " history:");
                int i = 3;
                Deque<Client> deque = room.getHistoryClientQueue();
                while (i != 0 && !deque.isEmpty()) {
                    System.out.print(deque.pollLast().getFullName() + " ");
                    i--;
                }
                System.out.println();
            }
        }
    }
}
