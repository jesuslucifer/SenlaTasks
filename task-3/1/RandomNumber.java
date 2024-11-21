//Variant 4
public class RandomNumber {
    public static void main(String[] args) {
        int a = new java.util.Random().nextInt(999);

        while(a < 100)
        {
            a = new java.util.Random().nextInt(999);
        }
        //System.out.println(a);
        System.out.println(a % 10 + a / 10 % 10 + a / 100);
    }
}
