public class TestThread extends Thread {
    private final Object lock;

    public TestThread(Object lock) {
        this.lock = lock;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            synchronized (lock) {
                try {
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(500);
                    lock.notify();
                    lock.wait();
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }
}
