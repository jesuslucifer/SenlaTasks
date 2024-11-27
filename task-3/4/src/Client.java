public class Client {
    private String passport;
    private String fullName;

    public Client(String passport, String fullName) {
        this.passport = passport;
        this.fullName = fullName;
    }

    public String getPassport() {
        return passport;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }
}
