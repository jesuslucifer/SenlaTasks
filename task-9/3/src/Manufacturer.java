import java.util.Random;

public class Manufacturer extends Thread {
    private final Buffer buffer;
    private final Random random;

    public Manufacturer(Buffer buffer) {
        this.buffer = buffer;
        random = new Random();
    }

    public void run() {
        while (true) {
            try {
                int product = random.nextInt(100);
                buffer.add(product);
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
