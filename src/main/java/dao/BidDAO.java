package dao;
import models.Bid;

import java.math.BigDecimal;
import java.sql.*;
import java.util.UUID;

public class BidDAO {
    private static final String TABLE_NAME = "bids";
    private Connection conn;

    public BidDAO(Connection conn) {
        this.conn = conn;
    }

    public Bid getFromID(UUID bid_id) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE id = " + bid_id);
        rs.next();
        UUID bidder_id = UUID.fromString(rs.getString("bidder_id"));
        UUID product_id = UUID.fromString(rs.getString("product_id"));
        BigDecimal amount = rs.getBigDecimal("amount");
        Date time = rs.getDate("date");
        return new Bid(bid_id, bidder_id, product_id, amount, time);
    }


}