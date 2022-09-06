package models;

import java.security.NoSuchAlgorithmException;

public class Administrator extends User {

    public Administrator(String username, String password, String email, long id) throws NoSuchAlgorithmException {
        super(username, password, email, id);
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
