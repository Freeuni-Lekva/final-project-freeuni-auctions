package models;

import java.security.NoSuchAlgorithmException;

public class RegularUser extends User {


    public RegularUser(long id, String username, String password, String email, String image, long balance, boolean isSuspended) throws NoSuchAlgorithmException {
        super(id, username, password, email, image, balance, isSuspended);
    }

    // used for creating users, so 'isSuspended' is automatically false.
    public RegularUser(String username, String password, String email, String image, long balance) throws NoSuchAlgorithmException {
        super(username, password, email, image, balance, false);
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
