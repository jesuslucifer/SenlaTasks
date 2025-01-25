package dao;

import connection.DatabaseConnection;
import model.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ServiceDAO implements IGenericDAO<Service> {
    private Connection connection;

    public ServiceDAO() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void create(Service service) {
        String query = "INSERT INTO Services (serviceName, cost) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
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
    public void update(Service entity) {

    }

    @Override
    public void delete(Service entity) {

    }

    @Override
    public List<Service> findAll() {
        return List.of();
    }
}
