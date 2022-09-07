package models;

import java.security.NoSuchAlgorithmException;

public class Administrator extends User {

    public Administrator(long id, String username, String password, String email, String image, long balance) throws NoSuchAlgorithmException {
        super(id, username, password, email, image, balance);
    }

    @Override
    public Role getRole() {
        return Role.Administator;
    }

    @Override
    public boolean isPremium() {
        return false;
    }

    @Override
    public void makeChanges() {

    }
}
