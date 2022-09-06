package models;

import java.security.NoSuchAlgorithmException;

public class RegularUser extends User {
    public RegularUser(String username, String password, String email, long id) throws NoSuchAlgorithmException {
        super(username, password, email, id);
    }

    @Override
    public Role getRole() {
        return Role.Regular;
    }

    @Override
    public boolean isPremium() {
        return false;
    }

    @Override
    public void makeChanges() {

    }
}
