public class Car implements IProduct{

    @Override
    public void installFirstPart(IProductPart productPart) {
        System.out.println("BodyCar is install");
    }

    @Override
    public void installSecondPart(IProductPart productPart) {
        System.out.println("Chassis is install");
    }

    @Override
    public void installThirdPart(IProductPart productPart) {
        System.out.println("Engine is install");
    }
}
