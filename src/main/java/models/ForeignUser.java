package models;

import java.security.NoSuchAlgorithmException;

public class ForeignUser extends User {

    public ForeignUser(String username, String password, String email, long id) throws NoSuchAlgorithmException {
        super(username, password, email, id);
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
}
