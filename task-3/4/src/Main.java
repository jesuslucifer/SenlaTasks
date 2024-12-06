import controller.HotelController;
import model.Client;
import model.Hotel;
import view.HotelView;

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

        HotelController hc = new HotelController(new Hotel(), new HotelView());
        hc.printRooms("CapcityI", "free");


        List<Client> clients = new ArrayList<>();

        hc.addService("Massage", 100);
        hc.addService("Bathhouse", 200);
        hc.addService("Pizza", 300);
        hc.addService("Bowling", 150);
        hc.addService("Billiard", 220);

        clients.add(new Client("321114", "Popov I.V."));
        clients.add(new Client("123411", "Popova O.S."));
        clients.add(new Client("431122", "Popov A.I."));
        hc.checkIntoRoom(clients, "10-11-2024", "15-11-2024");
        clients.clear();

        clients.add(new Client("545412", "Petrov E.A."));
        clients.add(new Client("545412", "Petrova F.Q."));
        hc.checkIntoRoom(clients, "01-04-2024", "23-04-2024");
        clients.clear();

        clients.add(new Client("312122", "Frolov F.K."));
        hc.checkIntoRoom(clients, "09-05-2024", "20-05-2024");
        clients.clear();

        clients.add(new Client("312122", "Gorbova F.A."));
        hc.checkIntoRoom(clients, "20-03-2024", "21-03-2024");
        clients.clear();

        clients.add(new Client("312122", "Filatov A.F."));
        hc.checkIntoRoom(clients, "28-06-2024", "29-06-2024");
        clients.clear();

        clients.add(new Client("312122", "Tripov L.A."));
        hc.checkIntoRoom(clients, "01-07-2024", "30-07-2024");
        clients.clear();

        //Список номеров(сортировать по цене, вместимости, количеству звезд)
        hc.printRooms("CostI", "all");
        System.out.println();
        hc.printRooms("CostD", "all");
        System.out.println();
        hc.printRooms("CapacityI", "all");
        System.out.println();
        hc.printRooms("CapacityD", "all");
        System.out.println();
        hc.printRooms("StarsI", "all");
        System.out.println();
        hc.printRooms("StarsD", "all");
        System.out.println();

        //Список свободных номеров(сортировать по цене, вместимости, количеству звезд)
        hc.printRooms("CostI", "free");
        System.out.println();
        hc.printRooms("CostD", "free");
        System.out.println();
        hc.printRooms("CapacityI", "free");
        System.out.println();
        hc.printRooms("CapacityD", "free");
        System.out.println();
        hc.printRooms("StarsI", "free");
        System.out.println();
        hc.printRooms("StarsD", "free");
        System.out.println();

        //Список постояльцев и их номеров(сортировать по алфавиту, дате освобождения номера)
        hc.printClientList("AlphabetA");
        System.out.println();
        hc.printClientList("AlphabetZ");
        System.out.println();
        hc.printClientList("DateI");
        System.out.println();
        hc.printClientList("DateD");
        System.out.println();

        //Общее число свободных номеров
        hc.printCountFreeRoom();
        System.out.println();

        //Общее число постояльцев
        hc.printCountClients();
        System.out.println();

        //Список номеров которые будут свободны по определенной дате в будущем
        hc.printRoomFreeByDate("09-05-2024");
        System.out.println();

        //Сумму оплаты за номер которую должен оплатить постоялец
        hc.printCostPerRoom("Frolov F.K.");
        hc.printCostPerRoom("Gorbova F.A.");
        hc.printCostPerRoom("Popov I.V.");
        hc.printCostPerRoom("Petrov E.A.");
        System.out.println();

        //Посмотреть список услуг постояльца и их цену(сортировать по цене, дате)
        hc.addServiceForClient("Massage", "Popov I.V.", "13-11-2024");
        hc.addServiceForClient("Bowling", "Popov I.V.", "14-11-2024");
        hc.addServiceForClient("Pizza", "Popov I.V.", "12-11-2024");
        hc.printClientServices("Popov I.V.", "CostI");
        hc.printClientServices("Popov I.V.", "CostD");
        hc.printClientServices("Popov I.V.", "DateI");
        hc.printClientServices("Popov I.V.", "DateD");
        System.out.println();

        //Цены услуг и номеров ( сортировать по разделу, цене)
        hc.printRoomAndService("ChapterR");
        hc.printRoomAndService("ChapterS");
        hc.printRoomAndService("Cost");
        System.out.println();

        //Посмотреть детали отдельного номера
        hc.printInfoRoom(0);
        hc.printInfoRoom(1);
        hc.printInfoRoom(2);
        System.out.println();

        hc.evictFromRoom(0);
        hc.evictFromRoom(1);
        hc.evictFromRoom(2);
        hc.evictFromRoom(3);
        hc.evictFromRoom(4);
        hc.evictFromRoom(5);
        hc.evictFromRoom(6);
        hc.evictFromRoom(7);
        hc.evictFromRoom(8);
        hc.evictFromRoom(9);

        clients.add(new Client("321114", "Kopov I.V."));
        clients.add(new Client("123411", "Tropov O.S."));
        clients.add(new Client("431122", "Horkov A.I."));
        hc.checkIntoRoom(clients, "10-11-2024", "15-11-2024");
        clients.clear();

        clients.add(new Client("545412", "Chubash E.A."));
        clients.add(new Client("545412", "Tipok F.Q."));
        hc.checkIntoRoom(clients, "01-04-2024", "23-04-2024");
        clients.clear();

        clients.add(new Client("312122", "Pinor F.K."));
        hc.checkIntoRoom(clients, "09-05-2024", "20-05-2024");
        clients.clear();

        clients.add(new Client("312122", "Kobrova F.A."));
        hc.checkIntoRoom(clients, "20-03-2024", "21-03-2024");
        clients.clear();

        clients.add(new Client("312122", "Fragov A.F."));
        hc.checkIntoRoom(clients, "28-06-2024", "29-06-2024");
        clients.clear();

        clients.add(new Client("312122", "Badov L.A."));
        hc.checkIntoRoom(clients, "01-07-2024", "30-07-2024");
        clients.clear();

        hc.evictFromRoom(0);
        hc.evictFromRoom(1);
        hc.evictFromRoom(2);
        hc.evictFromRoom(3);
        hc.evictFromRoom(4);
        hc.evictFromRoom(5);
        hc.evictFromRoom(6);
        hc.evictFromRoom(7);
        hc.evictFromRoom(8);
        hc.evictFromRoom(9);

        //Посмотреть 3-х последнихпостояльцев номера и даты их пребывания
        hc.printHistoryRoom(0);
        hc.printHistoryRoom(1);
        hc.printHistoryRoom(2);
        hc.printHistoryRoom(3);
        hc.printHistoryRoom(4);
        hc.printHistoryRoom(5);
        hc.printHistoryRoom(6);
        hc.printHistoryRoom(7);
        hc.printHistoryRoom(8);
        hc.printHistoryRoom(9);
        System.out.println();

    }
}