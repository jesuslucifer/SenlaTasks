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
        connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void create(Service service) {
        String query = "INSERT INTO Services (serviceName, cost) VALUES (?, ?)";
        try {
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

    public Service toService(ResultSet resultSet) throws SQLException {
        return new Service(resultSet.getString("serviceName"), resultSet.getInt("cost"));
    }
}
