package models;

public class PremiumUser extends RegularUser {

    public PremiumUser(String firstName, String password, String lastName, long id, String email) {
        super(firstName, password, lastName, id, email);
    }

    @Override
    public boolean isPremium() {
        return true;
    }
}
