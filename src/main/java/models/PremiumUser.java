package models;

import java.security.NoSuchAlgorithmException;

public class PremiumUser extends RegularUser {

    public PremiumUser(long id, String username, String password, String email, String image, long balance) throws NoSuchAlgorithmException {
        super(id, username, password, email, image, balance);
    }

    @Override
    public boolean isPremium() {
        return true;
    }
}
