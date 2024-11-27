//Variant 4
public class RandomNumber {
    public static void main(String[] args) {
        java.util.Random rand = new java.util.Random();
        int a = rand.nextInt(900) + 100;

        //System.out.println(a);
        System.out.println(a % 10 + a / 10 % 10 + a / 100);
    }
}
