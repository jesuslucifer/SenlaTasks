package dao;

import connection.DatabaseConnection;
import model.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAO implements IGenericDAO<Service> {
    private Connection connection;
    private static final Logger logger = LoggerFactory.getLogger(ServiceDAO.class);

    public ServiceDAO() {
    }

    @Override
    public void create(Service service) {
        String query = "INSERT INTO Services (serviceName, cost) VALUES (?, ?)";
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, service.getServiceName());
            statement.setInt(2, service.getCost());
            statement.executeUpdate();
            logger.info("Service created");
        } catch (SQLException e) {
            logger.error("Error creating service: ", e);
        }
    }

    @Override
    public Service read(int id) {
        return null;
    }

    @Override
    public void update(Service service) {
        String query = "UPDATE Services SET cost = ? WHERE serviceName = ?";
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, service.getCost());
            preparedStatement.setString(2, service.getServiceName());
            preparedStatement.executeUpdate();
            logger.info("Service updated");
        } catch (SQLException e) {
            logger.error("Error updating service: ", e);
        }
    }

    @Override
    public void delete(Service entity) {

    }

    @Override
    public List<Service> findAll() {
        List<Service> services = new ArrayList<>();
        String query = "SELECT * FROM Services";
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                services.add(toService(resultSet));
            }
            logger.info("Services found");
        } catch (SQLException e) {
            logger.error("Error finding services: ", e);
        }
        return services;
    }

    public Service findServiceName(String serviceName) {
        String query = "SELECT * FROM Services WHERE serviceName = ?";
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, serviceName);
            ResultSet resultSet = preparedStatement.executeQuery();
            connection.commit();
            connection.setAutoCommit(true);
            if (resultSet.next()) {
                logger.info("Service found");
                return toService(resultSet);
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            logger.error("Error finding service: ", e);
        }
        return null;
    }

    public Service toService(ResultSet resultSet) throws SQLException {
        return new Service(resultSet.getInt("id"),
                           resultSet.getString("serviceName"),
                           resultSet.getInt("cost"));
    }
}
