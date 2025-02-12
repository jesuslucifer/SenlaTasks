package dao;

import connection.DatabaseConnection;
import lombok.extern.slf4j.Slf4j;
import model.Room;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RoomDAO implements IGenericDAO<Room> {
    Connection connection;

    public RoomDAO() {
    }

    @Override
    public void create(Room room) {
        String query = "INSERT INTO Rooms(roomNumber, cost, status, capacity, dateCheckIn, dateEvict, countStars)  VALUES (?, ?, ?, ?, ?, ?, ?)";
        printLogQuery(query);
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, room.getRoomNumber());
            statement.setInt(2, room.getCost());
            statement.setString(3, room.getStatus().toString());
            statement.setInt(4, room.getCapacity());
            statement.setNull(5, java.sql.Types.DATE);
            statement.setNull(6, java.sql.Types.DATE);
            statement.setInt(7, room.getCountStars());
            statement.execute();
            log.info("Room created ID {}", room.getId());
        } catch (SQLException e) {
            log.error("Error creating room: ", e);
        }
    }

//    @Override
//    public Room read(int id) {
//        return null;
//    }

    @Override
    public Room read(int roomNumber) {
        String query = "SELECT * FROM Rooms WHERE roomNumber = ?";
        printLogQuery(query);
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, roomNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                log.info("Room read ID {}", roomNumber);
                return toRoom(resultSet);
            }
        } catch (SQLException e) {
            log.error("Error reading room: ", e);
        }
        return null;
    }

    @Override
    public void update(Room room) {
        String query = "UPDATE Rooms SET cost = ?, status = ?, dateCheckIn = ?, dateEvict = ?, lockedChangeStatus = ?, " +
                "countRecordsHistory = ? WHERE roomNumber = ?";
        printLogQuery(query);
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, room.getCost());
            preparedStatement.setString(2, room.getStatus().toString());
            if (room.getDateCheckIn() != null) {
                preparedStatement.setDate(3, Date.valueOf(room.getDateCheckIn()));
            } else {
                preparedStatement.setNull(3, java.sql.Types.DATE);
            }
            if (room.getDateEvict() != null) {
                preparedStatement.setDate(4, Date.valueOf(room.getDateEvict()));
            } else {
                preparedStatement.setNull(4, java.sql.Types.DATE);
            }
            preparedStatement.setBoolean(5, room.getLockedChangeStatus());
            preparedStatement.setInt(6, room.getCountRecordsHistory());
            preparedStatement.setInt(7, room.getRoomNumber());
            preparedStatement.executeUpdate();
            log.info("Room updated ID {}", room.getId());
        } catch (SQLException e) {
            log.error("Error updating room: ", e);
        }
    }

    @Override
    public void delete(Room entity) {

    }

    @Override
    public List<Room> findAll() {
        String query = "SELECT * FROM Rooms";
        return getRooms(query);
    }

    private List<Room> getRooms(String query) {
        List<Room> rooms = new ArrayList<>();
        printLogQuery(query);
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                rooms.add(toRoom(resultSet));
                log.info("Room ID {}", rooms.getLast().getId());
            }
            log.info("Rooms found");
        } catch (SQLException e) {
            log.error("Error reading rooms: ", e);
        }
        return rooms;
    }

    public List<Room> findByStatus(String status) {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM Rooms WHERE status = ?";
        printLogQuery(query);
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, status);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                rooms.add(toRoom(resultSet));
                log.info("Rooms by status {} ID {}", rooms.getLast().getStatus(), rooms.getLast().getId());
            }
            log.info("Rooms found by status");
        } catch (SQLException e) {
            log.error("Error found rooms by status: ", e);
        }
        return rooms;
    }

    public Room toRoom(ResultSet resultSet) throws SQLException {
        Room room = new Room(
                resultSet.getInt("id"),
                resultSet.getInt("roomNumber"),
                resultSet.getInt("cost"),
                resultSet.getInt("countStars"),
                resultSet.getString("status"),
                resultSet.getInt("capacity"),
                resultSet.getBoolean("lockedChangeStatus"),
                resultSet.getInt("countRecordsHistory")
        );
        Date checkIn = resultSet.getDate("dateCheckIn");
        LocalDate localCheckIn = (checkIn != null) ? checkIn.toLocalDate() : null;

        Date checkOut = resultSet.getDate("dateEvict");
        LocalDate localCheckOut = (checkOut != null) ? checkOut.toLocalDate() : null;

        room.setDateCheckIn(localCheckIn);
        room.setDateEvict(localCheckOut);

        return room;
    }

    public List<Room> findFreeByDate(LocalDate date) {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM Rooms WHERE status != 'REPAIRED' AND ((dateCheckIn IS NULL OR dateEvict IS NULL) OR dateEvict < ?)";
        printLogQuery(query);
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, Date.valueOf(date));
            ResultSet resultSet = preparedStatement.executeQuery();
            connection.commit();
            connection.setAutoCommit(true);
            while (resultSet.next()) {
                rooms.add(toRoom(resultSet));
                log.info("Rooms free by date {} ID {}", date, rooms.getLast().getId());
            }
            log.info("Rooms free by date found");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            log.error("Error found room free by date: ", e);
        }
        return rooms;
    }

    public List<Room> findWithSort(String typeRoom, String typeSort) {
        String query = "SELECT * FROM Rooms WHERE status IN (" + typeRoom + ") ORDER BY " + typeSort;
        return getRooms(query);
    }

    public void printLogQuery(String query) {
        log.info("Try QUERY {}", query);
    }
}
