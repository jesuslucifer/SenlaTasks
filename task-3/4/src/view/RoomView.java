package view;

import model.Client;
import model.Room;

import java.time.LocalDate;
import java.util.Deque;
import java.util.List;

public class RoomView {

    public void printMenu() {
        System.out.println("\tMenu Room");
        System.out.println("1. Print all rooms");
        System.out.println("2. Print free rooms");
        System.out.println("3. Print info room");
        System.out.println("4. Check into room");
        System.out.println("5. Evict from room");
        System.out.println("6. Print history room");
        System.out.println("7. Print room free by date");
        System.out.println("8. Change status of room");
        System.out.println("9. Change cost of room");
        System.out.println("10. Import room");
        System.out.println("11. Import locked room");
        System.out.println("12. Back");
        System.out.println("0. Exit");

    }

    public void printSwitchRooms() {
        System.out.println("\tChoose type sort:");
        System.out.println("1. Capacity increase");
        System.out.println("2. Capacity decrease");
        System.out.println("3. Cost increase");
        System.out.println("4. Cost decrease");
        System.out.println("5. Stars increase");
        System.out.println("6. Stars decrease");
        System.out.println("7. Back");
    }

    public void printSwitchStatus() {
        System.out.println("\tChoose status:");
        System.out.println("1. Free");
        System.out.println("2. Busy");
        System.out.println("3. Repaired");
        System.out.println("4. Back");

    }

    public void printRooms(List<Room> rooms){
        for (Room room : rooms) {
            if (room.isBusy()) {
                System.out.println("Room: " + room.getRoomNumber() + " Status: " + room.getStatus() + " Stars: " +
                        room.getCountStars() + " Capacity: " + room.getCapacity() + " Current occupancy: " + room.getClentList().size() +
                        " Cost: " + room.getCost() + " ID: " + room.getId());
                continue;
            }
            System.out.println("Room: " + room.getRoomNumber() + " Status: " + room.getStatus() + " Stars: " +
                    room.getCountStars() + " Capacity: " + room.getCapacity() + " Cost: " + room.getCost() + " ID: " + room.getId());
        }
    }

    public void printInfoRoom(Room room) {
        if (room.isBusy()) {
            System.out.println("Room: " + room.getRoomNumber() + " Status: " + room.getStatus() + " Stars: " +
                    room.getCountStars() + " Capacity: " + room.getCapacity() + " Cost: " + room.getCost() + " ID: " + room.getId());
            System.out.println("Clients: ");
            for (Client client : room.getClentList()) {
                System.out.println("Full name: " + client.getFullName() + " date check in: " + client.getDateCheckIn() + " date evict: " + client.getDateEvict());
            }
            System.out.println();
        } else {
            System.out.println("Room: " + room.getRoomNumber() + " Status: " + room.getStatus() + " Stars: "
                    + room.getCountStars() + " Capacity: " + room.getCapacity() + " Cost: " + room.getCost());
        }
    }

    public void printHistoryRoom(Room room, Deque<Client> deque) {
        System.out.println("Room " + room.getRoomNumber() + " history:");
        int i = 3;
        while (i != 0 && !deque.isEmpty()) {
            System.out.println("Full name: " + deque.peekLast().getFullName()
                    + " Date check in: "+ deque.peekLast().getDateCheckIn()
                    + " Date evict: " + deque.pollLast().getDateEvict());
            i--;
        }
        System.out.println();
    }

    public void printRoomFreeByDate(LocalDate date, List<Room> rooms) {
        System.out.println("Free rooms by " + date);
        for (Room room : rooms) {
            System.out.println("Room: " + room.getRoomNumber() + " Status: " + room.getStatus() + " Stars: " +
                    room.getCountStars() + " Capacity: " + room.getCapacity() + " Cost: " + room.getCost() + " ID: " + room.getId());
        }
    }
}



