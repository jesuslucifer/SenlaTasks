import java.time.LocalDate;
import java.time.LocalTime;

public class TestThread extends Thread {
    private final int timeInterval;

    public TestThread(int timeInterval) {
        this.timeInterval = timeInterval;
    }


    public void run() {
        while (true) {
            System.out.println(LocalTime.now());

            try {
                Thread.sleep(timeInterval * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
