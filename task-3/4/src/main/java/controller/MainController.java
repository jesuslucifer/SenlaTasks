package controller;

import connection.DatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import view.Menu;
import model.Hotel;

import javax.imageio.IIOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainController {
    @Inject
    Hotel hotel;
    @Inject
    Menu menu;
    @Inject
    RoomController roomController;
    @Inject
    ClientController clientController;
    @Inject
    ServiceController serviceController;
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);


    public MainController() {
    }

    public void run() {
        if (!isHotelInitialized()) {
            hotel = new Hotel();
        } else {
            hotel = new Hotel(1);
        }
        DI.register(Hotel.class, hotel);
        DI.injectDependencies(this);
        roomController.importLockedRoomProperty();
        roomController.importCountRecordHistory();
        menu.printMenu();
    }

    public boolean isHotelInitialized() {
        String query = "SELECT COUNT(*) FROM Rooms";
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public Hotel loadHotelFromSave() {
        try (ObjectInputStream os = new ObjectInputStream(new FileInputStream("task-3/4/src/resources/save.dat"))) {
            return (Hotel) os.readObject();
        } catch (FileNotFoundException | IIOException | ClassNotFoundException e) {
            return new Hotel();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
