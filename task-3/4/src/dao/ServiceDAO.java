package dao;

import connection.DatabaseConnection;
import model.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAO implements IGenericDAO<Service> {
    private Connection connection;

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
        } catch (SQLException e) {
            System.err.println(e.getMessage());
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
        } catch (SQLException e) {
            System.err.println(e.getMessage());
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
        } catch (SQLException e) {
            System.err.println(e.getMessage());
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
                return toService(resultSet);
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.err.println(e.getMessage());
        }
        return null;
    }

    public Service toService(ResultSet resultSet) throws SQLException {
        return new Service(resultSet.getInt("id"),
                           resultSet.getString("serviceName"),
                           resultSet.getInt("cost"));
    }
}
