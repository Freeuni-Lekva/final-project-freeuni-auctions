package models;

import java.security.NoSuchAlgorithmException;

public class PremiumUser extends RegularUser {

    public PremiumUser(String firstName, String password, String lastName, long id, String email) throws NoSuchAlgorithmException {
        super(firstName, password, lastName, id, email);
    }

    @Override
    public boolean isPremium() {
        return true;
    }
}
