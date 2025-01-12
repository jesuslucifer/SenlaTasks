public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        Consumer consumer = new Consumer(buffer);
        Manufacturer manufacturer = new Manufacturer(buffer);

        Thread consumerThread = new Thread(consumer);
        Thread manufacturerThread = new Thread(manufacturer);

        manufacturerThread.start();
        consumerThread.start();

    }
}
