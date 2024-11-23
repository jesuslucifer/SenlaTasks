public class BodyCarLineStep implements ILineStep {
    @Override
    public IProductPart buildProductPart() {
        return new BodyCar();
    }
}
