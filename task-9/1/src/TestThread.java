public class TestThread extends Thread {
    public void run() {
        nothing();

        Thread t1 = new Thread(new ThreadWaiting());
        t1.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized void nothing() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
