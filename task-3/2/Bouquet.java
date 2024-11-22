public class Bouquet {
    private int cost = 0;
    private int maxCountFlowers = 5;
    private int countFlowers = 0;
    private final Flowers[] flowers = new Flowers[maxCountFlowers];

    public Bouquet(int maxCountFlowers)
    {
        this.maxCountFlowers = maxCountFlowers;
    }

    public void addFlower(Flowers flower)
    {
        if (maxCountFlowers != countFlowers)
        {
            flowers[countFlowers] = flower;
            cost += flower.cost;
            countFlowers++;
        }
    }

    public int getCost()
    {
        return cost;
    }
}
