public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new TestThread());
        Thread t2 = new Thread(new TestThread());
        System.out.println("State: " + t1.getState());
        t2.start();
        t1.start();
        System.out.println("State: " + t1.getState());
        Thread.sleep(500);
        System.out.println("State: " + t1.getState());
        Thread.sleep(1000);
        System.out.println("State: " + t1.getState());
        Thread.sleep(1000);
        System.out.println("State: " + t1.getState());
        t1.join();
        System.out.println("State: " + t1.getState());
    }
}