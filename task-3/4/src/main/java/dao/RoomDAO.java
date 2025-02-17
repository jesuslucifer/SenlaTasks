package dao;

import lombok.extern.slf4j.Slf4j;
import model.Room;
import model.RoomStatus;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.time.LocalDate;
import java.util.List;

@Slf4j
public class RoomDAO implements IGenericDAO<Room> {
    private Session session;

    public RoomDAO() {
    }

    @Override
    public void create(Room room) {
        Transaction tx = null;
        try {
            session = HibernateUtil.getInstance().getSession();
            tx = session.beginTransaction();
            session.persist(room);
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
        Transaction tx = null;
        try {
            session = HibernateUtil.getInstance().getSession();
            tx = session.beginTransaction();
            Room room = session.get(Room.class, roomNumber);
            tx.commit();
            return room;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            log.error("Error reading room", e);
        }
        return null;
    }

    @Override
    public void update(Room room) {
        Transaction tx = null;
        try {
            session = HibernateUtil.getInstance().getSession();
            tx = session.beginTransaction();
            session.merge(room);
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
        try {
            session = HibernateUtil.getInstance().getSession();
            return session.createQuery("FROM Room", Room.class).list();
        } catch (Exception e) {
            log.error("Error finding all rooms", e);
            throw new RuntimeException(e);
        }
    }

    private List<Room> getRooms(String queryType, String typeSort) {
        try {
            session = HibernateUtil.getInstance().getSession();
            if (queryType.equals("ALL")) {
                String query = "FROM Room WHERE status IN (:status1, :status2, :status3) ORDER BY " + typeSort;
                return session.createQuery(query, Room.class)
                        .setParameter("status1", RoomStatus.FREE)
                        .setParameter("status2", RoomStatus.BUSY)
                        .setParameter("status3", RoomStatus.REPAIRED)
                        .list();
            } else {
                String query = "FROM Room WHERE status = :status ORDER BY " + typeSort;
                return session.createQuery(query, Room.class)
                        .setParameter("status", RoomStatus.FREE)
                        .list();
            }
        } catch (Exception e) {
            log.error("Error getting rooms", e);
            throw new RuntimeException(e);
        }
    }

    public List<Room> findByStatus(RoomStatus status) {
        try {
            session = HibernateUtil.getInstance().getSession();
            return session.createQuery("FROM Room WHERE status = :status", Room.class)
                    .setParameter("status", status)
                    .list();
        } catch (Exception e) {
            log.error("Error getting rooms", e);
            throw new RuntimeException(e);
        }
    }

    public List<Room> findFreeByDate(LocalDate date) {
        try {
            session = HibernateUtil.getInstance().getSession();
            return session
                    .createQuery("FROM Room WHERE status != :status AND ((dateCheckIn IS NULL OR dateEvict IS NULL) OR dateEvict < :date)", Room.class)
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
}
