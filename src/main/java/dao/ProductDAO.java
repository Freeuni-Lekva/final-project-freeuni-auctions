package dao;

import models.Product;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductDAO implements DAO{
    private static final String TABLE_NAME = "products";
    private Connection conn;

    public ProductDAO(Connection conn) {
        this.conn = conn;
    }

    public Product getFromID(UUID product_id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE id = ?");
            stmt.setString(1, product_id.toString());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return getSingleProduct(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> getProductsByCategory(UUID category_id) {
        List<Product> res = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE category_id = ? ORDER BY date_posted desc");
            stmt.setString(1, category_id.toString());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                res.add(getSingleProduct(rs));
            }
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    private Product getSingleProduct(ResultSet rs) {
        try {
            UUID product_id = UUID.fromString(rs.getString("product_id"));
            String name = rs.getString("name");
            UUID account_id = UUID.fromString(rs.getString("account_id"));
            UUID category_id = UUID.fromString(rs.getString("category_id"));
            UUID bid_id = UUID.fromString(rs.getString("bid_id"));
            BigDecimal currPrice = BigDecimal.valueOf(rs.getDouble("price"));
            String description = rs.getString("description");
            String status = rs.getString("status");
            boolean isAvailable = status.equals("available");
            Date datePosted = rs.getDate("date_posted");
            Date endDate = rs.getDate("end_date");

            return new Product(product_id, account_id, category_id, name,
                    description, bid_id, currPrice, isAvailable, datePosted, endDate);
        }   catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}