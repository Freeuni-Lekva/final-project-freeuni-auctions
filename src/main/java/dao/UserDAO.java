package dao;

import models.*;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDAO{
    private static final String TABLE_NAME = "users";
    public static final String ATTRIBUTE = "UserDAO";
    private Connection conn;

    public UserDAO(Connection conn) {
        this.conn = conn;
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
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String password = rs.getString("password");
        String email = rs.getString("email");
        Role role = Role.valueOf(rs.getString("role"));
        switch(role) {
            case Administator:
                return new Administrator(firstName, password, lastName, id, email);
            case Regular:
                return new RegularUser(firstName, password, lastName, id, email);
        }
        return null;
    }
    public List<User> getUserByNameAndLastName(String firstName, String lastName) throws SQLException, NoSuchAlgorithmException {
        List<User> res = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE first_name = ? and last_name = ?");
        stmt.setString(1,firstName);
        stmt.setString(2, lastName);
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
                "VALUES ( ?, ?, ?, ?, ?, ?, ?)");
        stmt.setLong(1, user.getId());
        stmt.setString(2, user.getFirstName());
        stmt.setString(3, user.getLastName());
        stmt.setString(4, user.getPassword());
        stmt.setString(5, user.getRole().toString());
        stmt.setBoolean(6, user.isPremium());
        stmt.setString(7, user.getEmail());
        stmt.executeQuery();
        stmt.close();
    }
    public boolean correct(String email, String password) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE email = ?");
        stmt.setString(1,email);
        ResultSet rs = stmt.executeQuery();
        boolean empty = rs.next();
        stmt.close();
        return empty;
    }
}
