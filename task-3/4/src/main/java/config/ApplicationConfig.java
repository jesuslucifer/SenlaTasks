package config;

import controller.ClientController;
import controller.HotelController;
import controller.MainController;
import controller.RoomController;
import controller.ServiceController;
import dao.ClientDAO;
import dao.RoomDAO;
import dao.ServiceDAO;
import model.Hotel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import view.ClientView;
import view.HotelView;
import view.Menu;
import view.MenuClient;
import view.MenuHotel;
import view.MenuRoom;
import view.MenuService;
import view.RoomView;
import view.ServiceView;

@Configuration
@PropertySource("classpath:config.properties")
public class ApplicationConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public ClientDAO clientDAO() {
        return new ClientDAO();
    }

    @Bean
    public RoomDAO roomDAO() {
        return new RoomDAO();
    }

    @Bean
    public ServiceDAO serviceDAO() {
        return new ServiceDAO();
    }

    @Bean
    public Hotel hotel() {
        return new Hotel();
    }

    @Bean
    public MainController mainController() {
        return new MainController(hotel(), menu(), roomController(), clientController(), serviceController());
    }

    @Bean
    public HotelController hotelController() {
        return new HotelController(hotel(), hotelView(), roomDAO(), serviceDAO(), clientDAO());
    }

    @Bean
    public RoomController roomController() {
        return new RoomController(hotel(), hotelController(), roomDAO(), clientDAO(), roomView());
    }

    @Bean
    public ServiceController serviceController() {
        return new ServiceController(hotel(), serviceDAO(), serviceView());
    }

    @Bean
    public ClientController clientController() {
        return new ClientController(hotel(), hotelController(), clientDAO(), serviceDAO(), roomDAO(), clientView());
    }

    @Bean
    public ClientView clientView() {
        return new ClientView();
    }

    @Bean
    public HotelView hotelView() {
        return new HotelView();
    }

    @Bean
    public RoomView roomView() {
        return new RoomView();
    }

    @Bean
    public ServiceView serviceView() {
        return new ServiceView();
    }

    @Bean
    public Menu menu() {
        return new Menu(menuRoom(), menuHotel(), menuClient(), menuService());
    }

    @Bean
    public MenuRoom menuRoom() {
        return new MenuRoom(roomController(), roomView());
    }

    @Bean
    public MenuClient menuClient() {
        return new MenuClient(clientController(), serviceController(), clientView());
    }

    @Bean
    public MenuService menuService() {
        return new MenuService(serviceController(), serviceView());
    }

    @Bean
    public MenuHotel menuHotel() {
        return new MenuHotel(hotelController(), hotelView());
    }
 }
