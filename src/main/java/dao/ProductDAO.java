package dao;

import models.Product;
import models.Status;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductDAO extends DAO{
    private static final String TABLE_NAME = "products";
    private static final String ATTRIBUTE = "productDAO";
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
            stmt.close();
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
            stmt.close();
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> getProductsByUser(UUID user_id) {
        List<Product> res = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE user_id = ? ORDER BY date_posted desc");
            stmt.setString(1, user_id.toString());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                res.add(getSingleProduct(rs));
            }
            stmt.close();
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> getProductsByStatus(Status status) {
        List<Product> res = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE status = ? ORDER BY date_posted desc");
            stmt.setString(1, status.getStatusString());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                res.add(getSingleProduct(rs));
            }
            stmt.close();
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void addProduct(Product p) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO " + TABLE_NAME +
                    "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?");
            stmt.setString(1, SQLWrapUUID(p.getId()));
            stmt.setString(2, p.getName());
            stmt.setString(3, p.getAccountId().toString());
            stmt.setString(4, p.getDescription());
            stmt.setString(5, p.getCategoryId().toString());
            stmt.setString(6, p.getBidId().toString());
            stmt.setBigDecimal(7, p.getCurrPrice());
            stmt.setString(8, p.getStatus());
            stmt.setDate(9, (Date) p.getDatePosted());
            stmt.setDate(10, (Date) p.getEndDate());
            stmt.executeQuery();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    private Product getSingleProduct(ResultSet rs) {
        try {
            UUID product_id = UUID.fromString(rs.getString("id"));
            String name = rs.getString("product_name");
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