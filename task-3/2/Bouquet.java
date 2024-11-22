public class Bouquet {
    private int coast = 0;
    private int maxCountFlowers = 5;
    private final Flowers[] flowers = new Flowers[maxCountFlowers];

    public Bouquet(int maxCountFlowers)
    {
        this.maxCountFlowers = maxCountFlowers;
    }

    public void addFlower(Flowers flower)
    {
        int countFlowers = 0;
        if (maxCountFlowers != countFlowers)
        {
            flowers[countFlowers] = flower;
            coast += flower.coast;
            countFlowers++;
        }
    }

    public int getCoast()
    {
        return coast;
    }
}
