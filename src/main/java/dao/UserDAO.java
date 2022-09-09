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
    public User getUserByID(long id, boolean isForeign) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE id = ?");
            stmt.setLong(1,id);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            return getUser(rs, isForeign, stmt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private User getUser(ResultSet rs, boolean isForeign, PreparedStatement stmt) {
        String username = null;
        try {
            username = rs.getString("username");
            String email = rs.getString("email");
            String image = rs.getString("image");
            if (isForeign) {
                try {
                    return new ForeignUser(username, email, image);
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
            }
            long id = Long.parseLong(rs.getString("id"));
            String password = rs.getString("password_hash");
            Role role = Role.valueOf(rs.getString("user_role"));
            long balance = rs.getLong("balance");
            switch(role) {
                case Administator:
                    try {
                        return new Administrator(id, username, password, email, image, balance);
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    }
                case Regular:
                    try {
                        return new RegularUser(id, username, password, email, image, balance);
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    }
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public User getUserByUsername(String username, boolean isForeign) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE username = ?");
            stmt.setString(1,username);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return getUser(rs, isForeign, stmt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public User getUserByEmail(String email, boolean isForeign) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE email = ?");
            stmt.setString(1,email);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return getUser(rs, isForeign, stmt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //id, firstName, LastName, Passwrod, Role, Premium, Email
    public void addUser(User user) {
        PreparedStatement stmt = null;
        try {
            if (containsId(user.getId())) {
                stmt = conn.prepareStatement("UPDATE " + TABLE_NAME + " SET "
                        + "username = ?, password_hash = ?, user_role = ?,"
                        + " premium = ?, email = ?, image = ?, balance = ?"
                        + " WHERE id = ?");

                stmt.setLong(8, user.getId());
            }   else {
                stmt = conn.prepareStatement("INSERT INTO " + TABLE_NAME + " (username, password_hash, user_role, premium, email, image, balance)" +
                        " VALUES ( ?, ?, ?, ?, ?, ?, ?)");
            }
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole().toString());
            stmt.setBoolean(4, user.isPremium());
            stmt.setString(5, user.getEmail());
            stmt.setString(6, user.getImage());
            stmt.setLong(7, user.getBalance());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean containsId(long id) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE id = ?");
            stmt.setLong(1,id);
            ResultSet rs = stmt.executeQuery();
            boolean empty = rs.next();
            stmt.close();
            return empty;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean containsEmail(String email) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE email = ?");
            stmt.setString(1,email);
            ResultSet rs = stmt.executeQuery();
            boolean empty = rs.next();
            stmt.close();
            return empty;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean correct(String email, String password) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE email = ? and password_hash = ?");
            stmt.setString(1,email);
            stmt.setString(2,password);
            ResultSet rs = stmt.executeQuery();
            boolean empty = rs.next();
            stmt.close();
            return empty;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean containsUsername(String username) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE username = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            boolean empty = rs.next();
            stmt.close();
            return empty;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
