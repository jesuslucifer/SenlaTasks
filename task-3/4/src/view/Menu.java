package view;

import java.util.Scanner;

public class Menu {
    private MenuRoom menuRoom;
    private MenuHotel menuHotel;
    private MenuClient menuClient;
    private MenuService menuService;
    private Scanner sc = new Scanner(System.in);

    public Menu(MenuRoom menuRoom, MenuService menuService, MenuHotel menuHotel, MenuClient menuClient) {
        this.menuRoom = menuRoom;
        this.menuService = menuService;
        this.menuHotel = menuHotel;
        this.menuClient = menuClient;
    }

    public void printMenu() {
        while (true) {
            System.out.println("\tMenu");
            System.out.println("1. Hotel");
            System.out.println("2. Room");
            System.out.println("3. Client");
            System.out.println("4. Service");
            System.out.println("5. Exit");

            selectMenu();
        }
    }

    public void selectMenu() {
            switch (sc.nextInt()) {
                case 1 -> menuHotel.printMenu();
                case 2 -> menuRoom.printMenu();
                case 3 -> menuClient.printMenu();
                case 4 -> menuService.printMenu();
                case 5 -> System.exit(0);
                default -> System.out.println("Invalid choice, try again");
            }
    }

    public int exitMenu() {
        return 0;
    }
}
