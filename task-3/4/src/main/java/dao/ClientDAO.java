package dao;

import connection.DatabaseConnection;
import lombok.extern.slf4j.Slf4j;
import model.Client;
import model.Service;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

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
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(client);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            log.error("Error creating client", e);
        }
    }

    @Override
    public Client read(int id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Client client = session.get(Client.class, id);
            tx.commit();
            return client;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            log.error("Error reading client", e);
        }
        return null;
    }

    @Override
    public void update(Client client) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(client);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            log.error("Error updating client", e);
        }
    }

    @Override
    public void delete(Client client) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.delete(client);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            log.error("Error deleting client", e);
        }
    }

    @Override
    public List<Client> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Client").list();
        } catch (Exception e) {
            log.error("Error finding all clients", e);
            throw new RuntimeException(e);
        }
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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Client WHERE roomNumber = :roomNumber", Client.class)
                    .setParameter("roomNumber", roomNumber)
                    .list();
        } catch (Exception e) {
            log.error("Error clients in room", e);
            throw new RuntimeException(e);
        }
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

    public List<Service> getServices(Client client, String typeSort) {
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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String query = "FROM Client WHERE occupied = true ORDER BY " + typeSort;
            return session.createQuery(query).list();
        } catch (Exception e) {
            log.error("Error finding all clients", e);
            throw new RuntimeException(e);
        }
    }

    public List<Client> printHistory(int roomNumber, int countRecordsHistory) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String query = "FROM Client WHERE roomNumber = :roomNumber AND occupied = false ORDER BY dateEvict DESC LIMIT :limit";
            return session.createQuery(query)
                    .setParameter("roomNumber", roomNumber)
                    .setParameter("limit", countRecordsHistory)
                    .list();
        } catch (Exception e) {
            log.error("Error finding all clients", e);
            throw new RuntimeException(e);
        }
    }

    public void printLogQuery(String query) {
        log.info("Try QUERY {}", query);
    }
}
