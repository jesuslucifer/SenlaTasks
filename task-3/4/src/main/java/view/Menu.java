package view;

import controller.ExitController;
import controller.Inject;

import java.util.Scanner;

public class Menu {
    @Inject
    MenuRoom menuRoom;
    @Inject
    MenuHotel menuHotel;
    @Inject
    MenuClient menuClient;
    @Inject
    MenuService menuService;
    private final Scanner sc = new Scanner(System.in);

    public Menu() {
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
