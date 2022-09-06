package dao;
import models.*;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BidDAO extends DAO{
    private static final String TABLE_NAME = "bids";
    private Connection conn;

    public BidDAO(Connection conn) {
        this.conn = conn;
    }

    public Bid getFromID(long bid_id) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE id = " + bid_id);
        if (rs.next()) {
            return createBidFromRow(rs);
        } else return null;
    }

    public List<Bid> getAllForUser(long user_id) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE bidder_id = " + user_id + " ORDER BY time DESC");
        List<Bid> result = new ArrayList<Bid>();
        while (rs.next()) {
            result.add(createBidFromRow(rs));
        }
        return result;
    }

    public List<Bid> getAllForProduct(long product_id) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE product_id = " + product_id + " ORDER BY time DESC");
        List<Bid> result = new ArrayList<Bid>();
        while (rs.next()) {
            result.add(createBidFromRow(rs));
        }
        return result;
    }

    private Bid createBidFromRow(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        long bidder_id = rs.getLong("bidder_id");
        long product_id = rs.getLong("product_id");
        BigDecimal amount = rs.getBigDecimal("amount");
        Timestamp bid_time = rs.getTimestamp("bid_time");
        return new Bid(id, bidder_id, product_id, amount, bid_time);
    }

    public void addNewBid(long bidder_id, long product_id, BigDecimal amount) throws SQLException {
        conn.createStatement().executeQuery("INSERT INTO " + TABLE_NAME + " VALUES (" + bidder_id + ", " + product_id + ", " + amount + ")");
    }
}