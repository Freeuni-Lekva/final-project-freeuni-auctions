package dao;

import models.*;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import java.util.UUID;

public class UserDAO{
    private static final String TABLE_NAME = "users";
    public static final String ATTRIBUTE = "UserDAO";
    private Connection conn;

    public UserDAO(Connection conn) {
        this.conn = conn;
    }
    public UserDAO() {

    }
    public User getUserByID(long id) throws SQLException, NoSuchAlgorithmException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE id = ?");
        stmt.setLong(1,id);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        stmt.close();
        return getUser(rs);

    }
    private User getUser(ResultSet rs) throws SQLException, NoSuchAlgorithmException {
        long id = Long.parseLong(rs.getString("id"));
        String username = rs.getString("username");
        String password = rs.getString("password");
        String email = rs.getString("email");
        Role role = Role.valueOf(rs.getString("role"));
        switch(role) {
            case Administator:
                return new Administrator(username, password, email, id);
            case Regular:
                return new RegularUser(username, password, email, id);
        }
        return null;
    }
    public List<User> getUserByUsername(String username) throws SQLException, NoSuchAlgorithmException {
        List<User> res = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE username = ?");
        stmt.setString(1,username);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            res.add(getUser(rs));
        }
        stmt.close();
        return res;
    }
    public User getUserByEmail(String email) throws SQLException, NoSuchAlgorithmException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE email = ?");
        stmt.setString(1,email);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        stmt.close();
        return getUser(rs);
    }
    //id, firstName, LastName, Passwrod, Role, Premium, Email
    public void addUser(User user) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO " + TABLE_NAME +
                "VALUES ( ?, ?, ?, ?, ?)");
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());
        stmt.setString(3, user.getRole().toString());
        stmt.setBoolean(4, user.isPremium());
        stmt.setString(5, user.getEmail());
        stmt.executeQuery();
        stmt.close();
    }
    public boolean contains(String email) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE email = ?");
        stmt.setString(1,email);
        ResultSet rs = stmt.executeQuery();
        boolean empty = rs.next();
        stmt.close();
        return empty;
    }
    public boolean correct(String email, String password) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE email = ? and password = ?");
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
