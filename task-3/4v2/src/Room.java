import java.util.List;
import java.util.ArrayList;

public class Room {
    private int roomNumber;
    private int cost;
    private String status;
    private int capacity;
    private List<Client> clentList = new ArrayList<Client>();

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.cost = 20;
        this.status = "free";
        this.capacity = new java.util.Random().nextInt(5) + 1;
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

    public void setClientList(Client client) {
        clentList.add(client);
    }

    public int getCapacity() {
        return capacity;
    }
}
