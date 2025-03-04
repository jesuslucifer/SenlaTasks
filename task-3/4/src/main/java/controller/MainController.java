package controller;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.HibernateUtil;
import view.Menu;
import model.Hotel;

@Component
@Slf4j
public class MainController {
    @Autowired
    Hotel hotel;
    @Autowired
    Menu menu;
    @Autowired
    RoomController roomController;
    @Autowired
    ClientController clientController;
    @Autowired
    ServiceController serviceController;


    public MainController() {
    }

    public void run() {
        log.info("Starting Program");
        if (!isHotelInitialized()) {
            hotel = new Hotel();
        } else {
            hotel = new Hotel(1);
        }
        roomController.importLockedRoomProperty();
        roomController.importCountRecordHistory();
        menu.printMenu();
    }

    public boolean isHotelInitialized() {
        try {
            Session session = HibernateUtil.getInstance().getSession();
            Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Room");
            if (query.uniqueResult() > 0) {
                log.info("Hotel initialized");
                return true;
            }
        } catch (HibernateException e) {
            log.error("Error initializing Hotel", e);
        }
        return false;
    }
}
