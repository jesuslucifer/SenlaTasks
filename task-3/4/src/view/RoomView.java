package view;

import model.Client;
import model.Room;

import java.time.LocalDate;
import java.util.Deque;
import java.util.List;

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
            System.out.println("Room: " + room.getRoomNumber());
        }
    }
}



