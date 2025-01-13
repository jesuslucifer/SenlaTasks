import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
    public Queue<Integer> buffer = new LinkedList<>();
    public final int CAPACITY = 10;

    public synchronized void add(int n) throws InterruptedException {
        while (buffer.size() == CAPACITY) {
            System.out.println("Buffer fullest, waiting...");
            wait();
        }
        buffer.add(n);
        System.out.println("Adding " + n);
        notifyAll();
    }

    public synchronized void remove() throws InterruptedException {
        while (buffer.isEmpty()) {
            System.out.println("Buffer empty, waiting...");
            wait();
        }
        buffer.poll();
        System.out.println("Removing");
        notifyAll();
    }

    public int size() {
        return CAPACITY;
    }
}
