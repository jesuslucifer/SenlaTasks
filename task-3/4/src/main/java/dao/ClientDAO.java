package dao;

import connection.DatabaseConnection;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
public class ClientDAO implements IGenericDAO<Client> {

    public ClientDAO() {
    }

    @Override
    public void create(Client client) {
        String query = "INSERT INTO Clients (fullName, roomNumber, dateCheckIn, dateEvict) VALUES (?, ?, ?, ?)";
        printLogQuery(query);
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, client.getFullName());
            statement.setInt(2, client.getRoomNumber());
            statement.setDate(3, Date.valueOf(client.getDateCheckIn()));
            statement.setDate(4, Date.valueOf(client.getDateEvict()));
            statement.executeUpdate();
            log.info("Client created ID {}", client.getId());
        } catch (SQLException e) {
            log.error("Error creating client", e);
        }
    }

    @Override
    public Client read(int id) {
        String query = "SELECT * FROM Clients WHERE id = ?";
        printLogQuery(query);
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                log.info("Client read ID {}", id);
                return toClient(resultSet);
            }
        } catch (SQLException e) {
            log.error("Error reading client", e);
        }
        return null;
    }

    @Override
    public void update(Client client) {
        String query = "UPDATE Clients SET occupied = ? WHERE id = ?";
        printLogQuery(query);
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, client.getOccupied());
            preparedStatement.setInt(2, client.getId());
            preparedStatement.executeUpdate();
            log.info("Client updated ID {}", client.getId());
        } catch (SQLException e) {
            log.error("Error updating client: ", e);
        }
    }

    @Override
    public void delete(Client client) {
        String query = "DELETE FROM Clients WHERE id = ?";
        printLogQuery(query);
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, client.getId());
            statement.executeUpdate();
            log.info("Client deleted ID {}", client.getId());
        } catch (SQLException e) {
            log.error("Error deleting client: ", e);
        }
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM Clients";
        printLogQuery(query);
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                clients.add(toClient(resultSet));
                log.info("Client ID {}", clients.getLast().getId());
            }
            log.info("Clients found");
        } catch (SQLException e) {
            log.error("Error finding clients: ", e);
        }
        return clients;
    }

    public Client findFullName(String fullName) {
        String query = "SELECT * FROM Clients WHERE fullName = ?";
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
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
        String query = "SELECT * FROM Clients WHERE roomNumber = ? and occupied = true";
        printLogQuery(query);
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, roomNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                clients.add(toClient(resultSet));
                log.info("Client ID {} in room {}", clients.getLast().getId(), roomNumber);
            }
            log.info("Clients in room ID {} found", roomNumber);
        } catch (SQLException e) {
            log.error("Error finding clients in room: ", e);
        }
        return clients;
    }

    public void addService(Client client, Service service, LocalDate date) {
        String query = "INSERT INTO ClientService (clientId, serviceId, serviceDate) VALUES (?, ?, ?)";
        printLogQuery(query);
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, client.getId());
            preparedStatement.setInt(2, service.getId());
            preparedStatement.setDate(3, Date.valueOf(date));
            preparedStatement.executeUpdate();
            log.info("Service ID {} added for client ID {}", service.getId(), client.getId());
        } catch (SQLException e) {
            log.error("Error adding service for client: ", e);
        }
    }

    public List<Service> getServices (Client client, String typeSort) {
        List<Service> services = new ArrayList<>();
        String query = "SELECT s.id, s.serviceName, s.cost, cs.serviceDate FROM Services s " +
                       "JOIN ClientService cs ON s.id = cs.serviceId WHERE cs.clientId = ? ORDER BY " + typeSort;
        printLogQuery(query);
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
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
                log.info("Service ID {}", service.getId());
            }
            log.info("Services found for client ID {}", client.getId());
        } catch (SQLException e) {
            log.error("Error finding services for client: ", e);
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
        String query = "SELECT * FROM Clients WHERE occupied = true ORDER BY " + typeSort + " ";
        printLogQuery(query);
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                clients.add(toClient(resultSet));
                log.info("Client with sort {} ID {}", typeSort, clients.getLast().getId());
            }
            log.info("Clients found with sort {}", typeSort);
        } catch (SQLException e) {
            log.error("Error finding clients with sort: ", e);
        }
        return clients;
    }

    public List<Client> printHistory(int roomNumber, int countRecordsHistory) {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM Clients WHERE roomNumber = ? AND occupied = false ORDER BY dateEvict DESC LIMIT ?";
        printLogQuery(query);
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, roomNumber);
            preparedStatement.setInt(2, countRecordsHistory);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                clients.add(toClient(resultSet));
                log.info("Client ID {}", clients.getLast().getId());
            }
            log.info("Successfully printed history");
        } catch (SQLException e) {
            log.error("Error printing history: ", e);
        }
        return clients;
    }

    public void printLogQuery(String query) {
        log.info("Try QUERY {}", query);
    }
}
