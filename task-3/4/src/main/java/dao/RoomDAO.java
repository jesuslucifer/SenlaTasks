package dao;

import connection.DatabaseConnection;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import model.Room;
import model.RoomStatus;
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
public class RoomDAO implements IGenericDAO<Room> {
    Connection connection;

    public RoomDAO() {
    }

    @Override
    public void create(Room room) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(room);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            log.error("Error creating room", e);
        }
    }

    @Override
    public Room read(int roomNumber) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Room.class, roomNumber);
        }
    }

    @Override
    public void update(Room room) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(room);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            log.error("Error updating room", e);
        }
    }

    @Override
    public void delete(Room entity) {

    }

    @Override
    public List<Room> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Room").list();
        }
    }

    private List<Room> getRooms(String queryType, String typeSort) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            if (queryType.equals("ALL")) {
                String query = "FROM Room WHERE status IN (:status1, :status2, :status3) ORDER BY " + typeSort;
                return session.createQuery(query)
                        .setParameter("status1", RoomStatus.FREE)
                        .setParameter("status2", RoomStatus.BUSY)
                        .setParameter("status3", RoomStatus.REPAIRED)
                        .list();
            } else {
                String query = "FROM Room WHERE status = :status ORDER BY " + typeSort;
                return session.createQuery(query)
                        .setParameter("status", RoomStatus.FREE)
                        .list();
            }
        } catch (Exception e) {
            log.error("Error getting rooms", e);
            throw new RuntimeException(e);
        }
    }

    public List<Room> findByStatus(String status) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Room WHERE status = :status")
                    .setParameter("status", status)
                    .list();
        } catch (Exception e) {
            log.error("Error getting rooms", e);
            throw new RuntimeException(e);
        }
    }

    public Room toRoom(ResultSet resultSet) throws SQLException {
        Room room = new Room(
                resultSet.getInt("id"),
                resultSet.getInt("roomNumber"),
                resultSet.getInt("cost"),
                resultSet.getInt("countStars"),
                resultSet.getString("status"),
                resultSet.getInt("capacity"),
                resultSet.getBoolean("lockedChangeStatus"),
                resultSet.getInt("countRecordsHistory")
        );
        Date checkIn = resultSet.getDate("dateCheckIn");
        LocalDate localCheckIn = (checkIn != null) ? checkIn.toLocalDate() : null;

        Date checkOut = resultSet.getDate("dateEvict");
        LocalDate localCheckOut = (checkOut != null) ? checkOut.toLocalDate() : null;

        room.setDateCheckIn(localCheckIn);
        room.setDateEvict(localCheckOut);

        return room;
    }

    public List<Room> findFreeByDate(LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Room WHERE status != :status AND ((dateCheckIn IS NULL OR dateEvict IS NULL) OR dateEvict < :date)")
                    .setParameter("status", RoomStatus.REPAIRED)
                    .setParameter("date", date)
                    .list();
        } catch (Exception e) {
            log.error("Error getting rooms free by date ", e);
            throw new RuntimeException(e);
        }
    }

    public List<Room> findWithSort(String typeQuery, String typeSort) {
        return getRooms(typeQuery, typeSort);
    }

    public void printLogQuery(String query) {
        log.info("Try QUERY {}", query);
    }
}
