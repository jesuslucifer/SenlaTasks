package dao;

import connection.DatabaseConnection;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import model.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

@Slf4j
public class ServiceDAO implements IGenericDAO<Service> {
    private Connection connection;

    public ServiceDAO() {
    }

    @Override
    public void create(Service service) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(service);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            log.error("Error creating service", e);
        }
    }

    @Override
    public Service read(int id) {
        return null;
    }

    @Override
    public void update(Service service) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(service);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            log.error("Error updating service", e);
        }
    }

    @Override
    public void delete(Service entity) {

    }

    @Override
    public List<Service> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Service", Service.class);
            return query.getResultList();
        }
    }

    public Service findServiceName(String serviceName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Service WHERE serviceName = :serviceName", Service.class);
            query.setParameter("serviceName", serviceName);
            return (Service) query.getSingleResult();
        } catch (Exception e) {
            log.error("Error finding service", e);
            return null;
        }
    }

    public Service toService(ResultSet resultSet) throws SQLException {
        return new Service(resultSet.getInt("id"),
                           resultSet.getString("serviceName"),
                           resultSet.getInt("cost"));
    }

    public void printLogQuery(String query) {
        log.info("Try QUERY {}", query);
    }
}
