package dao;

import connection.DatabaseConnection;
import model.Room;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO implements IGenericDAO<Room> {
    private final Connection connection;

    public RoomDAO() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void create(Room room) {
        String query = "INSERT INTO Rooms(roomNumber, cost, status, capacity, dateCheckIn, dateEvict, countStars)  VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
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

//    @Override
//    public Room read(int id) {
//        return null;
//    }

    @Override
    public Room read(int roomNumber) {
        String query = "SELECT * FROM Rooms WHERE roomNumber = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, roomNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return toRoom(resultSet);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Room room) {
        String query = "UPDATE Rooms SET cost = ?, status = ?, dateCheckIn = ?, dateEvict = ? WHERE roomNumber = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, room.getCost());
            preparedStatement.setString(2, room.getStatus().toString());
            preparedStatement.setDate(3, Date.valueOf(room.getDateCheckIn()));
            preparedStatement.setDate(4, Date.valueOf(room.getDateEvict()));
            preparedStatement.setInt(5, room.getRoomNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(Room entity) {

    }

    @Override
    public List<Room> findAll() {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM Rooms";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                rooms.add(toRoom(resultSet));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return rooms;
    }

    public List<Room> findByStatus(String status) {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM Rooms WHERE status = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, status);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                rooms.add(toRoom(resultSet));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return rooms;
    }

    public Room toRoom(ResultSet resultSet) throws SQLException {
        return new Room(
                resultSet.getInt("id"),
                resultSet.getInt("roomNumber"),
                resultSet.getInt("cost"),
                resultSet.getInt("countStars"),
                resultSet.getString("status"),
                resultSet.getInt("capacity"),
                resultSet.getString("dateCheckIn"),
                resultSet.getString("dateEvict"));
    }

    public List<Room> findFreeByDate(LocalDate date) {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM Rooms WHERE status != 'REPAIRED' AND ((dateCheckIn IS NULL OR dateEvict IS NULL) OR dateEvict < ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, Date.valueOf(date));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                rooms.add(toRoom(resultSet));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return rooms;
    }
}
