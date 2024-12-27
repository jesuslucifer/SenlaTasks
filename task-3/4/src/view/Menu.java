package view;

import controller.SerializableController;

import java.util.Scanner;

public class Menu {
    private final MenuRoom menuRoom;
    private final MenuHotel menuHotel;
    private final MenuClient menuClient;
    private final MenuService menuService;
    private final Scanner sc = new Scanner(System.in);
    private final SerializableController serializableController;

    public Menu(MenuRoom menuRoom, MenuService menuService, MenuHotel menuHotel, MenuClient menuClient, SerializableController serializableController) {
        this.menuRoom = menuRoom;
        this.menuService = menuService;
        this.menuHotel = menuHotel;
        this.menuClient = menuClient;
        this.serializableController = serializableController;
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
                case 0 -> serializableController.exit();
                default -> System.out.println("Invalid choice, try again");
            }
        }
    }
}
