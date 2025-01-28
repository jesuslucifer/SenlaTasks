package dao;

import connection.DatabaseConnection;
import model.Client;
import model.Service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO implements IGenericDAO<Client> {
    private final Connection connection;

    public ClientDAO() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void create(Client client) {
        String query = "INSERT INTO Clients (fullName, roomNumber, dateCheckIn, dateEvict) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
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
        String query = "DELETE FROM Clients WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, client.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM Clients";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                clients.add(toClient(resultSet));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return clients;
    }

    public Client findFullName(String fullName) {
        String query = "SELECT * FROM Clients WHERE fullName = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, fullName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return toClient(resultSet);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public List<Client> findInRoom(int roomNumber) {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM Clients WHERE roomNumber = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, roomNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                clients.add(toClient(resultSet));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return clients;
    }

    public void addService(Client client, Service service, LocalDate date) {
        String query = "INSERT INTO ClientService (clientId, serviceId, serviceDate) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, client.getId());
            preparedStatement.setInt(2, service.getId());
            preparedStatement.setDate(3, Date.valueOf(date));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<Service> getServices (Client client, String typeSort) {
        List<Service> services = new ArrayList<>();
        String query = "SELECT s.id, s.serviceName, s.cost, cs.serviceDate FROM Services s " +
                       "JOIN ClientService cs ON s.id = cs.serviceId WHERE cs.clientId = ? ORDER BY " + typeSort;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, client.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Service service = new Service(
                        resultSet.getInt("id"),
                        resultSet.getString("serviceName"),
                        resultSet.getInt("cost"),
                        resultSet.getDate("serviceDate").toLocalDate()
                );
                services.add(service);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return services;
    }

    public Client toClient(ResultSet resultSet) throws SQLException {
        return new Client(
                resultSet.getInt("id"),
                resultSet.getString("fullName"),
                resultSet.getInt("roomNumber"),
                resultSet.getString("dateCheckIn"),
                resultSet.getString("dateEvict")
        );
    }

    public List<Client> findAllWithSort(String typeSort) {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM Clients ORDER BY " + typeSort + " ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                clients.add(toClient(resultSet));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return clients;
    }
}
