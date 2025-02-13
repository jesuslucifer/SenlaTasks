package model;

import dao.RoomDAO;

import java.io.Serial;
import java.io.Serializable;

public class Hotel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final RoomDAO roomDAO = new RoomDAO();

    public Hotel() {
        int COUNT_ROOMS = 11;
        for (int i = 1; i < COUNT_ROOMS; i++) {
            roomDAO.create(new Room(i));
        }
        System.out.println("The hotel is open, there are " + (COUNT_ROOMS - 1) + " rooms available");
    }

    public Hotel(int i) { }
}
