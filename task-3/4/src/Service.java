public class Service {
    private String serviceName;
    private int cost;

    public Service(String serviceName, int cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
