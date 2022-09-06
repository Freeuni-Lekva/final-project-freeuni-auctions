package models;

import java.security.NoSuchAlgorithmException;

public class PremiumUser extends RegularUser {

    public PremiumUser(String username, String password, String email, long id) throws NoSuchAlgorithmException {
        super(username, password, email, id);
    }

    @Override
    public boolean isPremium() {
        return true;
    }
}
