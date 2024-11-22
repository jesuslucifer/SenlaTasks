//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
            Bouquet bouquet = new Bouquet(5);

            bouquet.addFlower(new Lily(10));
            bouquet.addFlower(new Rose(25));
            bouquet.addFlower(new Lily(10));
            bouquet.addFlower(new Pion(5));
            bouquet.addFlower(new Tulip(30));

            System.out.println(bouquet.getCoast());
    }
}
