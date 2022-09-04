package models;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class User {
    private final String firstName;
    private String password;
    private final String lastName;
    private final String email;
    private final long id;
    public User(String firstName, String password, String lastName, long id, String email) throws NoSuchAlgorithmException {
        this.firstName = firstName;
        this.password = hashPassword(password);
        this.lastName = lastName;
        this.email = email;
        this.id = id;
    }

    private static String hexToString(byte[] bytes) {
        StringBuilder buff = new StringBuilder();
        for (int aByte : bytes) {
            int val = aByte;
            val = val & 0xff;  // remove higher bits, sign
            if (val < 16) buff.append('0'); // leading 0
            buff.append(Integer.toString(val, 16));
        }
        return buff.toString();
    }
    public static String hashPassword(String str) throws NoSuchAlgorithmException {
        MessageDigest dsg = MessageDigest.getInstance("SHA");
        byte[] digest = dsg.digest(str.getBytes());
        String res = hexToString(digest);
        return str;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }
    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
    public abstract Role getRole();
    public abstract boolean isPremium();
    public abstract void makeChanges();
}
