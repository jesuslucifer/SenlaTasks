public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(new TestThread(3));
        thread.start();
    }
}
