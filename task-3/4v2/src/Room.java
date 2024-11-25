public class Room {
    private int roomNumber;
    private int cost;
    private String status;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.cost = 20;
        this.status = "free";
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
}
