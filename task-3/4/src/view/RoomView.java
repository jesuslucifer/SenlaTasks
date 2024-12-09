package view;

import model.Client;
import model.Room;

import java.time.LocalDate;
import java.util.Deque;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class RoomView {

    public void printRooms(List<Room> rooms){
        for (Room room : rooms) {
            System.out.println("Room: " + room.getRoomNumber() + " Status: " + room.getStatus() + " Stars: " +
                    room.getCountStars() + " Capacity: " + room.getCapacity() + " Cost: " + room.getCost());
        }
    }

    public void printInfoRoom(Room room) {
        if (room.isBusy()) {
            System.out.println("Room: " + room.getRoomNumber() + " Status: " + room.getStatus() + " Stars: "
                    + room.getCountStars() + " Capacity: " + room.getCapacity() + " Cost: " + room.getCost());
            System.out.print("Clients: ");
            for (Client client : room.getClentList()) {
                System.out.print(client.getFullName() + " ");
            }
            System.out.println();
        } else {
            System.out.println("Room: " + room.getRoomNumber() + " Status: " + room.getStatus() + " Stars: "
                    + room.getCountStars() + " Capacity: " + room.getCapacity() + " Cost: " + room.getCost());
        }
    }

    public void printCostPerRoom(long cost) {
        System.out.println("Cost per room: " + cost);
    }

    public void printHistoryRoom(Room room) {
        System.out.println("Room " + room.getRoomNumber() + " history:");
        int i = 3;
        Deque<Client> deque = room.getHistoryClientQueue();
        while (i != 0 && !deque.isEmpty()) {
            System.out.print(deque.pollLast().getFullName() + " ");
            i--;
        }
        System.out.println();
    }

    public void printRoomFreeByDate(LocalDate date, List<Room> rooms) {
        System.out.println("Free rooms by " + date);
        for (Room room : rooms) {
            if (room.getDateEvict().isBefore(date)) {
                System.out.println("Room: " + room.getRoomNumber());
            }
        }
    }
}



