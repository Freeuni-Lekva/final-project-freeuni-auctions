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

    public Category getFromID(long category_id) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE id = " + category_id);
        if (rs.next()) {
            return new Category(rs.getLong("id"), rs.getString("name"));
        }
        return null;
    }

    public void addNewCategory(String category_name) throws SQLException {
        conn.createStatement().executeUpdate("INSERT INTO " + TABLE_NAME + " c (name) VALUES (" + category_name + ")");
    }

    public Category getFromName(String category_name) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM " + TABLE_NAME + " c WHERE c.name = " + category_name);
        if (rs.next()) {
            return new Category(rs.getLong("id"), rs.getString("name"));
        }
        return null;
    }
}