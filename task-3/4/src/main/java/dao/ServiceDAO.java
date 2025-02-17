package dao;

import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import model.Service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

@Slf4j
public class ServiceDAO implements IGenericDAO<Service> {
    private Session session;

    public ServiceDAO() {
    }

    @Override
    public void create(Service service) {
        Transaction tx = null;
        try {
            session = HibernateUtil.getInstance().getSession();
            tx = session.beginTransaction();
            session.persist(service);
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
        try {
            session = HibernateUtil.getInstance().getSession();
            tx = session.beginTransaction();
            session.merge(service);
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
        try {
            session = HibernateUtil.getInstance().getSession();
            Query query = session.createQuery("FROM Service", Service.class);
            return query.getResultList();
        } catch (Exception e) {
            log.error("Error find all services ", e);
        }
        return null;
    }

    public Service findServiceName(String serviceName) {
        try {
            session = HibernateUtil.getInstance().getSession();
            Query query = session.createQuery("FROM Service WHERE serviceName = :serviceName", Service.class);
            query.setParameter("serviceName", serviceName);
            return (Service) query.getSingleResult();
        } catch (Exception e) {
            log.error("Error finding service", e);
            return null;
        }
    }
}
