package models;

import java.security.NoSuchAlgorithmException;

public class PremiumUser extends RegularUser {

    public PremiumUser(long id, String username, String password, String email, String image) throws NoSuchAlgorithmException {
        super(id, username, password, email, image);
    }

    @Override
    public boolean isPremium() {
        return true;
    }

    @Override
    public long getBalance() {
        return super.getBalance();
    }

    @Override
    public long setBalance() {
        return super.setBalance();
    }
}
