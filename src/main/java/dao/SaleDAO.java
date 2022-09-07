package dao;

import models.Sale;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaleDAO extends DAO {
    private static final String TABLE_NAME = "sales";
    public static final String ATTRIBUTE = "SaleDAO";
    private Connection conn;

    public SaleDAO(Connection conn) {
        this.conn = conn;
    }

    public void addSale(Sale sale) {
        try {
            conn.createStatement().executeQuery("INSERT INTO " + TABLE_NAME + " VALUES (" + sale.getId() + ", "
                    + sale.getProduct_id() + ", " + sale.getUser_id() + ")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Sale getSaleBySaleID(long sale_id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE id = ?");
            stmt.setString(1, String.valueOf(sale_id));
            ResultSet rs = stmt.executeQuery();
            rs.next();
            stmt.close();
            return buildSaleFromRS(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Sale getSaleByProductID(long product_id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE product_id = ?");
            stmt.setString(1, String.valueOf(product_id));
            ResultSet rs = stmt.executeQuery();
            rs.next();
            stmt.close();
            return buildSaleFromRS(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Sale> getSalesByUserID(long user_id) {
        List<Sale> result = new ArrayList<>();
        try {
            ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM " + TABLE_NAME
                    + " WHERE user_id = " + user_id);
            while (resultSet.next()) {
                result.add(buildSaleFromRS(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private Sale buildSaleFromRS(ResultSet resultSet) {
        try {
            long sale_id = resultSet.getLong("id");
            long product_id = resultSet.getLong("product_id");
            long user_id = resultSet.getLong("user_id");
            Timestamp date = resultSet.getTimestamp("date");
            double price = resultSet.getDouble("price");
            return new Sale(sale_id, product_id, user_id, date, price);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}