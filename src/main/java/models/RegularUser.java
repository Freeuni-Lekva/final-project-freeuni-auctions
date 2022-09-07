package models;

import java.security.NoSuchAlgorithmException;

public class RegularUser extends User {

    public RegularUser(long id, String username, String password, String email, String image, long balance) throws NoSuchAlgorithmException {
        super(id, username, password, email, image, balance);
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
