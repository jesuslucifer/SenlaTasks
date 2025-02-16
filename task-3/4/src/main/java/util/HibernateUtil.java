package util;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

@Slf4j
public final class HibernateUtil {
    private Session session;
    private static HibernateUtil instance;

    private HibernateUtil() {
        try {
            session = new Configuration().configure().buildSessionFactory().openSession();
        } catch (Exception e) {
            log.error("Error open session", e);
        }
    }

    public static HibernateUtil getInstance() {
        if (instance == null) {
            instance = new HibernateUtil();
        }
        return instance;
    }

    public Session getSession() {
        return session;
    }
}
