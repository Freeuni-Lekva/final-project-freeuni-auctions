package dao;
import models.Category;

import java.sql.*;
import java.util.UUID;

public class CategoryDAO {
    public static final String ATTRIBUTE = "CategoryDAO";
    private static final String TABLE_NAME = "categories";
    private Connection conn;

    public CategoryDAO(Connection conn) {
        this.conn = conn;
    }

    public Category getFromID(long category_id) {
        ResultSet rs = null;
        try {
            rs = conn.createStatement().executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE id = " + category_id);
            if (rs.next()) {
                return new Category(rs.getLong("id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public void addNewCategory(String category_name) {
        try {
            PreparedStatement st = conn.prepareStatement("INSERT INTO " + TABLE_NAME + " (name) VALUES (?)");
            st.setString(1, category_name);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Category getFromName(String category_name) {
        ResultSet rs = null;
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE name = ?");
            st.setString(1, category_name);
            rs = st.executeQuery();
            if (rs.next()) {
                return new Category(rs.getLong("id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}