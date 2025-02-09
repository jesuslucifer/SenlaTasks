package dao;

import connection.DatabaseConnection;
import model.Client;
import model.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO implements IGenericDAO<Client> {
    private static final Logger logger = LoggerFactory.getLogger(ClientDAO.class);

    public ClientDAO() {
    }

    @Override
    public void create(Client client) {
        String query = "INSERT INTO Clients (fullName, roomNumber, dateCheckIn, dateEvict) VALUES (?, ?, ?, ?)";
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, client.getFullName());
            statement.setInt(2, client.getRoomNumber());
            statement.setDate(3, Date.valueOf(client.getDateCheckIn()));
            statement.setDate(4, Date.valueOf(client.getDateEvict()));
            statement.executeUpdate();
            logger.info("Client created");
        } catch (SQLException e) {
            logger.error("Error creating client", e);
        }
    }

    @Override
    public Client read(int id) {
        String query = "SELECT * FROM Clients WHERE id = ?";
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                logger.info("Client read");
                return toClient(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error reading client", e);
        }
        return null;
    }

    @Override
    public void update(Client client) {
        String query = "UPDATE Clients SET occupied = ? WHERE id = ?";
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, client.getOccupied());
            preparedStatement.setInt(2, client.getId());
            preparedStatement.executeUpdate();
            logger.info("Client updated");
        } catch (SQLException e) {
            logger.error("Error updating client: ", e);
        }
    }

    @Override
    public void delete(Client client) {
        String query = "DELETE FROM Clients WHERE id = ?";
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, client.getId());
            statement.executeUpdate();
            logger.info("Client deleted");
        } catch (SQLException e) {
            logger.error("Error deleting client: ", e);
        }
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM Clients";
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                clients.add(toClient(resultSet));
            }
            logger.info("Clients found");
        } catch (SQLException e) {
            logger.error("Error finding clients: ", e);
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
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, roomNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                clients.add(toClient(resultSet));
            }
            logger.info("Clients in room found");
        } catch (SQLException e) {
            logger.error("Error finding clients in room: ", e);
        }
        return clients;
    }

    public void addService(Client client, Service service, LocalDate date) {
        String query = "INSERT INTO ClientService (clientId, serviceId, serviceDate) VALUES (?, ?, ?)";
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, client.getId());
            preparedStatement.setInt(2, service.getId());
            preparedStatement.setDate(3, Date.valueOf(date));
            preparedStatement.executeUpdate();
            logger.info("Service added for client");
        } catch (SQLException e) {
            logger.error("Error adding service for client: ", e);
        }
    }

    public List<Service> getServices (Client client, String typeSort) {
        List<Service> services = new ArrayList<>();
        String query = "SELECT s.id, s.serviceName, s.cost, cs.serviceDate FROM Services s " +
                       "JOIN ClientService cs ON s.id = cs.serviceId WHERE cs.clientId = ? ORDER BY " + typeSort;
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
            }
            logger.info("Services found");
        } catch (SQLException e) {
            logger.error("Error finding services for client: ", e);
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

        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                clients.add(toClient(resultSet));
            }
            logger.info("Clients found with sort {}", typeSort);
        } catch (SQLException e) {
            logger.error("Error finding clients with sort: ", e);
        }
        return clients;
    }

    public List<Client> printHistory(int roomNumber, int countRecordsHistory) {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM Clients WHERE roomNumber = ? AND occupied = false ORDER BY dateEvict DESC LIMIT ?";
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, roomNumber);
            preparedStatement.setInt(2, countRecordsHistory);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                clients.add(toClient(resultSet));
            }
            logger.info("Successfully printed history");
        } catch (SQLException e) {
            logger.error("Error printing history: ", e);
        }
        return clients;
    }
}
