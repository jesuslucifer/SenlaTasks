package dao;

import connection.DatabaseConnection;
import model.Client;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ClientDAO implements IGenericDAO<Client> {
    private Connection connection;

    public ClientDAO() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void create(Client client) {
        String query = "INSERT INTO Clients (fullName, roomNumber, dateCheckIn, dateEvict) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, client.getFullName());
            statement.setInt(2, client.getRoomNumber());
            statement.setDate(3, Date.valueOf(client.getDateCheckIn()));
            statement.setDate(4, Date.valueOf(client.getDateEvict()));
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Client read(int id) {
        return null;
    }

    @Override
    public void update(Client Client) {

    }

    @Override
    public void delete(Client client) {

    }

    @Override
    public List<Client> findAll() {
        return List.of();
    }
}
