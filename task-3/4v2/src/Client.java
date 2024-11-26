public class Client {
    private int passport;
    private String fullName;

    public Client(int passport, String fullName) {
        this.passport = passport;
        this.fullName = fullName;
    }

    public int getPassport() {
        return passport;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassport(int passport) {
        this.passport = passport;
    }
}
