package dao;

import models.*;

import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class UserDAO{
    private static final String TABLE_NAME = "users";
    public static final String ATTRIBUTE = "UserDAO";
    private Connection conn;

    public UserDAO(Connection conn) {

        this.conn = conn;
    }
    public UserDAO() {
        conn = DBConnection.getInstance();
    }
    public User getUserByID(long id, boolean isForeign) throws SQLException, NoSuchAlgorithmException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE id = ?");
        stmt.setLong(1,id);
        ResultSet rs = stmt.executeQuery();
        rs.next();

        return getUser(rs, isForeign, stmt);

    }
    private User getUser(ResultSet rs, boolean isForeign, PreparedStatement stmt) throws SQLException, NoSuchAlgorithmException {
        String username = rs.getString("username");
        String email = rs.getString("email");
        String image = rs.getString("image");
        if (isForeign) {
            return new ForeignUser(username, email, image);
        }
        long id = Long.parseLong(rs.getString("id"));
        String password = rs.getString("password_hash");
        Role role = Role.valueOf(rs.getString("user_role"));
        long balance = rs.getLong("balance");
        switch(role) {
            case Administator:
                return new Administrator(id, username, password, email, image, balance);
            case Regular:
                return new RegularUser(id, username, password, email, image, balance);
        }
        stmt.close();
        return null;
    }
    public User getUserByUsername(String username, boolean isForeign) throws SQLException, NoSuchAlgorithmException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE username = ?");
        stmt.setString(1,username);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        return getUser(rs, isForeign, stmt);
    }
    public User getUserByEmail(String email, boolean isForeign) throws SQLException, NoSuchAlgorithmException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE email = ?");
        stmt.setString(1,email);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        return getUser(rs, isForeign, stmt);
    }
    //id, firstName, LastName, Passwrod, Role, Premium, Email
    public void addUser(User user) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO " + TABLE_NAME + " (username, password_hash, user_role, premium, email, image, balance)" +
                " VALUES ( ?, ?, ?, ?, ?, ?, ?)");
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());
        stmt.setString(3, user.getRole().toString());
        stmt.setBoolean(4, user.isPremium());
        stmt.setString(5, user.getEmail());
        stmt.setString(6, user.getImage());
        stmt.setLong(7, user.getBalance());
        stmt.executeUpdate();
        stmt.close();
    }
    public boolean containsEmail(String email) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE email = ?");
        stmt.setString(1,email);
        ResultSet rs = stmt.executeQuery();
        boolean empty = rs.next();
        stmt.close();
        return empty;
    }
    public boolean correct(String email, String password) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE email = ? and password_hash = ?");
        stmt.setString(1,email);
        stmt.setString(2,password);
        ResultSet rs = stmt.executeQuery();
        boolean empty = rs.next();
        stmt.close();
        return empty;
    }

    public boolean containsUsername(String username) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE username = ?");
        stmt.setString(1,username);
        ResultSet rs = stmt.executeQuery();
        boolean empty = rs.next();
        stmt.close();
        return empty;
    }
}
