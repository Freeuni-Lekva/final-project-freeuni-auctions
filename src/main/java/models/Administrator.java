package models;

import java.security.NoSuchAlgorithmException;

public class Administrator extends User {

    public Administrator(String firstName, String password, String lastName, long id, String email) throws NoSuchAlgorithmException {
        super(firstName, password, lastName, id, email);
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
