package controller;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import util.HibernateUtil;
import view.Menu;
import model.Hotel;

@Component
@Slf4j
public class MainController {
    private Hotel hotel;
    private final Menu menu;
    private final RoomController roomController;
    private final ClientController clientController;
    private final ServiceController serviceController;


    public MainController(Hotel hotel, Menu menu, RoomController roomController, ClientController clientController, ServiceController serviceController) {
        this.hotel = hotel;
        this.menu = menu;
        this.roomController = roomController;
        this.clientController = clientController;
        this.serviceController = serviceController;
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
