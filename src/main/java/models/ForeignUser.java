package models;

import java.security.NoSuchAlgorithmException;

public class ForeignUser extends User {

    public ForeignUser(String username, String email, String image) throws NoSuchAlgorithmException {
        super(-1, username, null, email, image, -1);
    }

    @Override
    public Role getRole() {
        return null;
    }

    @Override
    @Deprecated
    public String getPassword() {
        return "";
    }

    @Override
    @Deprecated
    public long getId() {
        return -1;
    }

    @Deprecated
    public boolean isPremium() {
        return false;
    }

    @Override
    public void makeChanges() {

    }

    @Deprecated
    public long getBalance() {
        return -1;
    }

    @Deprecated
    public long setBalance() {
        return -1;
    }
}
