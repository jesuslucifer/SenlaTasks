import java.util.List;
import java.util.ArrayList;

public class Room {
    private int roomNumber;
    private int cost;
    private int countStars;
    private String status;
    private int capacity;
    private List<Client> clentList = new ArrayList<Client>();

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.countStars = new java.util.Random().nextInt(5) + 1;
        this.status = "free";
        this.capacity = new java.util.Random().nextInt(3) + 1;
        switch (countStars) {
            case 1: cost = 10; break;
            case 2: cost = 20; break;
            case 3: cost = 30; break;
            case 4: cost = 40; break;
            case 5: cost = 50; break;
        }
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Client> getClentList() {
        return clentList;
    }

    public void setClientList(List<Client> clients) {
        clentList.addAll(clients);
    }

    public int getCapacity() {
        return capacity;
    }
}
