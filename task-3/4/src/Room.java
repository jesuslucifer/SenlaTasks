import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Room {
    private int roomNumber;
    private int cost;
    private int countStars;
    private String status;
    private int capacity;
    private List<Client> clentList = new ArrayList<Client>();
    private LocalDate dateCheckIn;
    private LocalDate dateEvict;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.countStars = new java.util.Random().nextInt(5) + 1;
        this.status = "free";
        this.capacity = new java.util.Random().nextInt(3) + 1;
        this.cost = (capacity + countStars) * 10;
        dateCheckIn = LocalDate.of(2020, 1, 1);
        dateEvict = LocalDate.of(2020, 1, 1);
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

    public int getCountStars() {
        return countStars;
    }

    public int getCost(){
        return cost;
    }

    public void setDateCheckIn(LocalDate date) {
        dateCheckIn = date;
    }

    public LocalDate getDateCheckIn() {
        return dateCheckIn;
    }

    public void setDateEvict(LocalDate date) {
        dateEvict = date;
    }

    public LocalDate getDateEvict() {
        return dateEvict;
    }
}
