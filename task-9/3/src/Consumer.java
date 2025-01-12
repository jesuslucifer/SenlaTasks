import java.util.Random;

public class Consumer extends Thread {
    private final Buffer buffer;
    private final Random random;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
        random = new Random();
    }

    public void run() {
        while (true) {
            try {
                buffer.remove();
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
