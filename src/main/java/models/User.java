package models;


public abstract class User {
    private final String firstName;
    private String password;
    private final String lastName;
    private final String email;
    private final long id;
    public User(String firstName, String password, String lastName, long id, String email) {
        this.firstName = firstName;
        this.password = hashPassword(password);
        this.lastName = lastName;
        this.email = email;
        this.id = id;
    }
    private String hashPassword(String str){

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
