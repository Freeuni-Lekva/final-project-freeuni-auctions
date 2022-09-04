package dao;
import models.Category;

import java.sql.*;
import java.util.UUID;

public class CategoryDAO {
    public static final String ATTRIBUTE = "categoryDAO";
    private static final String TABLE_NAME = "categories";
    private Connection conn;

    public CategoryDAO(Connection conn) {
        this.conn = conn;
    }

    public Category getFromID(UUID category_id) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE id = " + category_id);
        rs.next();
        String name = rs.getString("name");
        return new Category(category_id, name);
    }

    public void addNewCategory(String name) throws SQLException {
        conn.createStatement().executeQuery("INSERT INTO " + TABLE_NAME + " VALUES (unhex(replace(uuid(),'-','')), " + name + ")");
    }

    public Category getFromName(String name) throws SQLException {
        // TODO
        return null;
    }
}