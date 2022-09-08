package dao;

import models.Product;
import models.Status;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends DAO{
    private static final String TABLE_NAME = "products";
    public static final String ATTRIBUTE = "productDAO";
    private Connection conn;

    public ProductDAO(Connection conn) {
        this.conn = conn;
    }

    public Product getFromID(long product_id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE id = ?");
            stmt.setLong(1, product_id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            stmt.close();
            return getSingleProduct(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> getProductsByCategory(long category_id) {
        List<Product> res = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE category_id = ? ORDER BY date_posted desc");
            stmt.setLong(1, category_id);
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

    public List<Product> getProductsByUser(long user_id) {
        List<Product> res = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE user_id = ? ORDER BY date_posted desc");
            stmt.setLong(1, user_id);
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
            stmt.setString(1, status.toString());
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

    public void addProduct(Product p) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO " + TABLE_NAME +
                    " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setLong(1, p.getId());
            stmt.setString(2, p.getName());
            stmt.setLong(3, p.getAccountId());
            stmt.setString(4, p.getDescription());
            stmt.setLong(5, p.getCategoryId());
            stmt.setLong(6, p.getBidId());
            stmt.setBigDecimal(7, p.getCurrPrice());
            stmt.setString(8, p.getStatus().toString());
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
            long product_id = rs.getLong("id");
            String name = rs.getString("product_name");
            long account_id = rs.getLong("account_id");
            long category_id = rs.getLong("category_id");
            long bid_id = rs.getLong("bid_id");
            BigDecimal currPrice = BigDecimal.valueOf(rs.getDouble("price"));
            String description = rs.getString("description");
            Status status = Status.valueOf(rs.getString("status"));
            Date datePosted = rs.getDate("date_posted");
            Date endDate = rs.getDate("end_date");
            String image = rs.getString("image");
            return new Product(product_id, account_id, category_id, name,
                    description, bid_id, currPrice, status, datePosted, endDate, image);
        }   catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public ArrayList<Product> getProductsByName(String phrase) {
        //TODO
        return new ArrayList<>();
    }
}