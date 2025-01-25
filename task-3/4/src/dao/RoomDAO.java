package dao;

import connection.DatabaseConnection;
import model.Room;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RoomDAO implements IGenericDAO<Room> {
    private Connection connection;

    public RoomDAO() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void create(Room room) {
        String query = "INSERT INTO Rooms(roomNumber, cost, status, capacity, dateCheckIn, dateEvict, countStars)  VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, room.getRoomNumber());
            statement.setInt(2, room.getCost());
            statement.setString(3, room.getStatus().toString());
            statement.setInt(4, room.getCapacity());
            statement.setDate(5, Date.valueOf(room.getDateCheckIn()));
            statement.setDate(6, Date.valueOf(room.getDateEvict()));
            statement.setInt(7, room.getCountStars());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Room read(int id) {
        return null;
    }

    @Override
    public void update(Room entity) {

    }

    @Override
    public void delete(Room entity) {

    }

    @Override
    public List<Room> findAll() {
        return List.of();
    }
}
