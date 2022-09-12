package models;

import java.security.NoSuchAlgorithmException;

public class ForeignUser extends User {
    public static final String ATTRIBUTE = "foreignUser";
    public ForeignUser(long id, String username, String email, String image) throws NoSuchAlgorithmException {
        super(id, username, null, email, image, -1);
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


    @Deprecated
    public boolean isPremium() {
        return false;
    }

    @Override
    public boolean makeChanges() {
        return false;
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
