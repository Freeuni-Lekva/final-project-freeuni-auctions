package models;

import java.security.NoSuchAlgorithmException;

public class ForeignUser extends User {

    public ForeignUser(long id, String username, String password, String email, String image) throws NoSuchAlgorithmException {
        super(id, username, password, email, image);
    }

    @Override
    public Role getRole() {
        return null;
    }

    @Override
    public boolean isPremium() {
        return false;
    }

    @Override
    public void makeChanges() {

    }

    @Override
    public long getBalance() {
        return 0;
    }

    @Override
    public long setBalance() {
        return 0;
    }
}
