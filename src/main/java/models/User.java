package models;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public abstract class User {
    private long id;
    private String username;
    private String password;
    private final String email;
    private String image;
    private long balance;

    public static final String ATTRIBUTE = "User";

    public User(long id, String username, String password, String email, String image, long balance) throws NoSuchAlgorithmException {
        this.id = id;
        this.username = username;
        this.password = hashPassword(password);
        this.email = email;
        this.image = image;
        this.balance = balance;
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
        if (str == null) {
            return null;
        }
        MessageDigest dsg = MessageDigest.getInstance("SHA");
        byte[] digest = dsg.digest(str.getBytes());
        String res = hexToString(digest);
        return str;
    }

    public String getUsername() {
        return username;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public  long getBalance() {
        return this.balance;
    }
    public  void setBalance(long amount) {
        this.balance = amount;
    }

    public abstract Role getRole();
    public abstract boolean isPremium();
    public abstract void makeChanges();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && balance == user.balance && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(image, user.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, image, balance);
    }

    public void setId(long id) {
        this.id = id;
    }
}
