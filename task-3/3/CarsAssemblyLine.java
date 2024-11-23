public class CarsAssemblyLine implements IAssemblyLine{
    private ILineStep firstStep;
    private ILineStep secondStep;
    private ILineStep thirdStep;

    CarsAssemblyLine(ILineStep firstStep, ILineStep secondStep, ILineStep thirdStep) {
        this.firstStep = firstStep;
        this.secondStep = secondStep;
        this.thirdStep = thirdStep;
    }

    @Override
    public IProduct assembleProduct(IProduct product) {
        IProductPart firstPart = firstStep.buildProductPart();
        IProductPart secondPart = secondStep.buildProductPart();
        IProductPart thirdPart = thirdStep.buildProductPart();

        product.installFirstPart(firstPart);
        product.installSecondPart(secondPart);
        product.installThirdPart(thirdPart);

        System.out.println("Car is assembled");
        return product;
    }
}
