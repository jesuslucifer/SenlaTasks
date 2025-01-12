public class Main {
    public static void main(String[] args) {
        Object lock = new Object();

        Thread thread = new Thread(new TestThread(lock));
        Thread thread2 = new Thread(new TestThread(lock));

        thread.start();
        thread2.start();

    }
}