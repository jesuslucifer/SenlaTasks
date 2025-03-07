package view;

import controller.ExitController;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Menu {
    private final MenuRoom menuRoom;
    private final MenuHotel menuHotel;
    private final MenuClient menuClient;
    private final MenuService menuService;
    private final Scanner sc = new Scanner(System.in);

    public Menu(MenuRoom menuRoom, MenuHotel menuHotel, MenuClient menuClient, MenuService menuService) {
        this.menuRoom = menuRoom;
        this.menuHotel = menuHotel;
        this.menuClient = menuClient;
        this.menuService = menuService;
    }

    public void printMenu() {
        while (true) {
            System.out.println("\tMenu");
            System.out.println("1. Hotel");
            System.out.println("2. Room");
            System.out.println("3. Client");
            System.out.println("4. Service");
            System.out.println("0. Exit");

            switch (sc.nextInt()) {
                case 1 -> menuHotel.printMenu();
                case 2 -> menuRoom.printMenuPageOne();
                case 3 -> menuClient.printMenu();
                case 4 -> menuService.printMenu();
                case 0 -> ExitController.exit();
                default -> System.out.println("Invalid choice, try again");
            }
        }
    }
}
