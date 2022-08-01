package dao;
import models.*;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;

public class BidDAO extends DAO{
    private static final String TABLE_NAME = "bids";
    private Connection conn;

    public BidDAO(Connection conn) {
        this.conn = conn;
    }

    public Bid getFromID(UUID bid_id) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE id = " + bid_id);
        if (rs.next()) {
            return createBidFromRow(rs);
        } else return null;
    }

    public List<Bid> getAllForUser(UUID user_id) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE bidder_id = " + SQLWrapUUID(user_id) + " ORDER BY bid_time DESC");
        List<Bid> result = new ArrayList<Bid>();
        while (rs.next()) {
            result.add(createBidFromRow(rs));
        }
        return result;
    }

    public List<Bid> getAllForProduct(UUID product_id) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE product_id = " + product_id + " ORDER BY time DESC");
        List<Bid> result = new ArrayList<Bid>();
        while (rs.next()) {
            result.add(createBidFromRow(rs));
        }
        return result;
    }

    private Bid createBidFromRow(ResultSet rs) throws SQLException {
        UUID id = binaryToUUID(rs.getBytes("id"));
        UUID bidder_id = binaryToUUID(rs.getBytes("bidder_id"));
        UUID product_id = binaryToUUID(rs.getBytes("product_id"));
        BigDecimal amount = rs.getBigDecimal("amount");
        Date time = rs.getDate("date");
        return new Bid(id, bidder_id, product_id, amount, time);
    }

    public void addNewBid(UUID bidder_id, UUID product_id, BigDecimal amount) throws SQLException {
        String sql = "INSERT INTO " + TABLE_NAME + "(id, bidder_id, product_id, amount) VALUES (" + SQLWrapUUID(null) + ", "
                + SQLWrapUUID(bidder_id) + ", " + SQLWrapUUID(product_id) + ", " + amount + ")";
        conn.createStatement().executeUpdate(sql);
    }
}