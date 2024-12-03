import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /* Дата вводится в формате "ДД-ММ-ГГГГ" - "01-01-2001"

           printRooms - параметр typeRoom значение "free" для вывода свободных номеров
                        параметр typeSort допустимые значения: "CapacityI" - сортировка по вместимости по возрастанию
                                                               "CapacityD" - сортировка по вместимости по убыванию
                                                               "CostI" - сортировка по стоимости по возрастанию
                                                               "CostD" - сортировка по стоимости по убыванию
                                                               "StarsI" - сортировка по кол-ву звезд по возрастанию
                                                               "StarsD" - сортировка по кол-ву звезд по убыванию

          printClientServices - параметр typeSort допустимые значения: "AlphabetA" - сортировка по алфавиту по возрастанию
                                                                       "AlphabetZ" - сортировка по алфавиту по убыванию
                                                                       "DateI" - сортировка по дате по возрастанию
                                                                       "DateD" - сортировка по дате по убыванию

          printRoomAndService - параметр typeSort допустимые значения: "ChapterR" - вывод номера потом услуги
                                                                       "ChapterS" - вывод услуг потом номеров
                                                                       "Cost" - сортировка по цене

          printClientServices - параметр typeSort допустимые значения: "CostI" - сортировка по стоимости по возрастанию
                                                                       "CostD" - сортировка по стоимости по убыванию
                                                                       "DateI" - сортировка по дате по возрастанию
                                                                       "DateD" - сортировка по дате по убыванию
        * */

        Hotel hotel = new Hotel();

        List<Client> clients = new ArrayList<>();

        hotel.addService("Massage", 100);
        hotel.addService("Bathhouse", 200);
        hotel.addService("Pizza", 300);
        hotel.addService("Bowling", 150);
        hotel.addService("Billiard", 220);

        clients.add(new Client("321114", "Popov I.V."));
        clients.add(new Client("123411", "Popova O.S."));
        clients.add(new Client("431122", "Popov A.I."));
        hotel.checkIntoRoom(clients, "10-11-2024", "15-11-2024");
        clients.clear();

        clients.add(new Client("545412", "Petrov E.A."));
        clients.add(new Client("545412", "Petrova F.Q."));
        hotel.checkIntoRoom(clients, "01-04-2024", "23-04-2024");
        clients.clear();

        clients.add(new Client("312122", "Frolov F.K."));
        hotel.checkIntoRoom(clients, "09-05-2024", "20-05-2024");
        clients.clear();

        clients.add(new Client("312122", "Gorbova F.A."));
        hotel.checkIntoRoom(clients, "20-03-2024", "21-03-2024");
        clients.clear();

        clients.add(new Client("312122", "Filatov A.F."));
        hotel.checkIntoRoom(clients, "28-06-2024", "29-06-2024");
        clients.clear();

        clients.add(new Client("312122", "Tripov L.A."));
        hotel.checkIntoRoom(clients, "01-07-2024", "30-07-2024");
        clients.clear();

        //Список номеров(сортировать по цене, вместимости, количеству звезд)
        hotel.printRooms("CostI", "all");
        System.out.println();
        hotel.printRooms("CostD", "all");
        System.out.println();
        hotel.printRooms("CapacityI", "all");
        System.out.println();
        hotel.printRooms("CapacityD", "all");
        System.out.println();
        hotel.printRooms("StarsI", "all");
        System.out.println();
        hotel.printRooms("StarsD", "all");
        System.out.println();

        //Список свободных номеров(сортировать по цене, вместимости, количеству звезд)
        hotel.printRooms("CostI", "free");
        System.out.println();
        hotel.printRooms("CostD", "free");
        System.out.println();
        hotel.printRooms("CapacityI", "free");
        System.out.println();
        hotel.printRooms("CapacityD", "free");
        System.out.println();
        hotel.printRooms("StarsI", "free");
        System.out.println();
        hotel.printRooms("StarsD", "free");
        System.out.println();

        //Список постояльцев и их номеров(сортировать по алфавиту, дате освобождения номера)
        hotel.printClientList("AlphabetA");
        System.out.println();
        hotel.printClientList("AlphabetZ");
        System.out.println();
        hotel.printClientList("DateI");
        System.out.println();
        hotel.printClientList("DateD");
        System.out.println();

        //Общее число свободных номеров
        hotel.printCountFreeRoom();
        System.out.println();

        //Общее число постояльцев
        hotel.printCountClients();
        System.out.println();

        //Список номеров которые будут свободны по определенной дате в будущем
        hotel.printRoomFreeByDate("09-05-2024");
        System.out.println();

        //Сумму оплаты за номер которую должен оплатить постоялец
        hotel.printCostPerRoom("Frolov F.K.");
        hotel.printCostPerRoom("Gorbova F.A.");
        hotel.printCostPerRoom("Popov I.V.");
        hotel.printCostPerRoom("Petrov E.A.");
        System.out.println();

        //Посмотреть список услуг постояльца и их цену(сортировать по цене, дате)
        hotel.addServiceForClient("Massage", "Popov I.V.", "13-11-2024");
        hotel.addServiceForClient("Bowling", "Popov I.V.", "14-11-2024");
        hotel.addServiceForClient("Pizza", "Popov I.V.", "12-11-2024");
        hotel.printClientServices("Popov I.V.", "CostI");
        hotel.printClientServices("Popov I.V.", "CostD");
        hotel.printClientServices("Popov I.V.", "DateI");
        hotel.printClientServices("Popov I.V.", "DateD");
        System.out.println();

        //Цены услуг и номеров ( сортировать по разделу, цене)
        hotel.printRoomAndService("ChapterR");
        hotel.printRoomAndService("ChapterS");
        hotel.printRoomAndService("Cost");
        System.out.println();

        //Посмотреть детали отдельного номера
        hotel.printInfoRoom(0);
        hotel.printInfoRoom(1);
        hotel.printInfoRoom(2);
        System.out.println();

        hotel.evictFromRoom(0);
        hotel.evictFromRoom(1);
        hotel.evictFromRoom(2);
        hotel.evictFromRoom(3);
        hotel.evictFromRoom(4);
        hotel.evictFromRoom(5);
        hotel.evictFromRoom(6);
        hotel.evictFromRoom(7);
        hotel.evictFromRoom(8);
        hotel.evictFromRoom(9);

        clients.add(new Client("321114", "Kopov I.V."));
        clients.add(new Client("123411", "Tropov O.S."));
        clients.add(new Client("431122", "Horkov A.I."));
        hotel.checkIntoRoom(clients, "10-11-2024", "15-11-2024");
        clients.clear();

        clients.add(new Client("545412", "Chubash E.A."));
        clients.add(new Client("545412", "Tipok F.Q."));
        hotel.checkIntoRoom(clients, "01-04-2024", "23-04-2024");
        clients.clear();

        clients.add(new Client("312122", "Pinor F.K."));
        hotel.checkIntoRoom(clients, "09-05-2024", "20-05-2024");
        clients.clear();

        clients.add(new Client("312122", "Kobrova F.A."));
        hotel.checkIntoRoom(clients, "20-03-2024", "21-03-2024");
        clients.clear();

        clients.add(new Client("312122", "Fragov A.F."));
        hotel.checkIntoRoom(clients, "28-06-2024", "29-06-2024");
        clients.clear();

        clients.add(new Client("312122", "Badov L.A."));
        hotel.checkIntoRoom(clients, "01-07-2024", "30-07-2024");
        clients.clear();

        hotel.evictFromRoom(0);
        hotel.evictFromRoom(1);
        hotel.evictFromRoom(2);
        hotel.evictFromRoom(3);
        hotel.evictFromRoom(4);
        hotel.evictFromRoom(5);
        hotel.evictFromRoom(6);
        hotel.evictFromRoom(7);
        hotel.evictFromRoom(8);
        hotel.evictFromRoom(9);

        //Посмотреть 3-х последнихпостояльцев номера и даты их пребывания
        hotel.printHistoryRoom(0);
        hotel.printHistoryRoom(1);
        hotel.printHistoryRoom(2);
        hotel.printHistoryRoom(3);
        hotel.printHistoryRoom(4);
        hotel.printHistoryRoom(5);
        hotel.printHistoryRoom(6);
        hotel.printHistoryRoom(7);
        hotel.printHistoryRoom(8);
        hotel.printHistoryRoom(9);
        System.out.println();

    }
}