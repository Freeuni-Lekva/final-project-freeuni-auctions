package dao;

import models.Product;
import models.Status;

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
            Product pr = getSingleProduct(rs);
            stmt.close();
            return pr;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> getProductsByCategory(long category_id) {
        List<Product> res = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE category_id = ? ORDER BY date_posted DESC");
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
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE user_id = ? ORDER BY date_posted DESC");
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

    /**
     * adds product if its id is not in the table, and updates the product otherwise.
     * @param p
     */
    public void addProduct(Product p) {
        try {
            PreparedStatement stmt;
            if (containsId(p.getId())) {
                stmt = conn.prepareStatement("UPDATE " + TABLE_NAME + " SET "
                        + "product_name = ?, user_id = ?, description = ?, category_id = ?, bid_id = ?,"
                        + " price = ?, status = ?, image = ?, date_posted = ?, end_date = ?"
                        + " WHERE id = ?");
                stmt.setLong(11, p.getId());
            }   else {
                stmt = conn.prepareStatement("INSERT INTO " + TABLE_NAME
                        + " (product_name, user_id, description, category_id, bid_id,"
                        + " price, status, image, date_posted, end_date)"
                        + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            }
            stmt.setString(1, p.getName());
            stmt.setLong(2, p.getUserId());
            stmt.setString(3, p.getDescription());
            stmt.setLong(4, p.getCategoryId());
            stmt.setLong(5, p.getBidId());
            stmt.setLong(6, p.getCurrPrice());
            stmt.setString(7, p.getStatus().toString());
            stmt.setString(8, p.getImage());
            stmt.setDate(9, new java.sql.Date(p.getDatePosted().getTime()));
            stmt.setDate(10, new java.sql.Date(p.getEndDate().getTime()));

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private boolean containsId(long id) {
        List<Long> res = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                res.add(rs.getLong(1));
            }
            stmt.close();
            return res.contains(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private Product getSingleProduct(ResultSet rs) {
        try {
            long product_id = rs.getLong("id");
            String name = rs.getString("product_name");
            long user_id = rs.getLong("user_id");
            long category_id = rs.getLong("category_id");
            long bid_id = rs.getLong("bid_id");
            long currPrice = rs.getLong("price");
            String description = rs.getString("description");
            Status status = Status.valueOf(rs.getString("status"));
            Date datePosted = rs.getDate("date_posted");
            Date endDate = rs.getDate("end_date");
            String image = rs.getString("image");
            return new Product(product_id, user_id, category_id, name,
                    description, bid_id, currPrice, status, datePosted, endDate, image);
        }   catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public ArrayList<Product> getProductsByName(String phrase) {
        ArrayList<Product> res = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE product_name LIKE ?");
            stmt.setString(1, phrase);
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

    public void refresh() {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE TABLE " + TABLE_NAME + " SET status = timed_out"
                    + " WHERE end_date < CURDATE()"
                    );
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setSold(Product product) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE TABLE " + TABLE_NAME + " SET status = sold"
                    + " WHERE id = ?");
            stmt.setLong(1, product.getId());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean containsProduct(String name) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE product_name = ?");
            stmt.setString(1,name);
            ResultSet rs = stmt.executeQuery();
            boolean empty = rs.next();
            stmt.close();
            return empty;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}