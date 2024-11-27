
public class Main {
    public static void main(String[] args) {
        ILineStep firstStep = new BodyCarLineStep();
        ILineStep secondStep = new ChassisLineStep();
        ILineStep thirdStep = new EngineLineStep();

        CarsAssemblyLine line = new CarsAssemblyLine(firstStep, secondStep, thirdStep);

        IProduct product = line.assembleProduct(new Car());
    }
}
