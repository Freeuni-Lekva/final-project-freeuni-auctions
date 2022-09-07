package models;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

public class Bid {
    private final long id, bidder_id, product_id;
    private final BigDecimal amount;
    private final Timestamp bid_time;

    public Bid(long id, long bidder_id, long product_id, BigDecimal amount, Timestamp bid_time) {
        this.id = id;
        this.bidder_id = bidder_id;
        this.product_id = product_id;
        this.amount = amount;
        this.bid_time = bid_time;
    }

    public long getId() {
        return id;
    }

    public long getBidder_id() {
        return bidder_id;
    }

    public long getItem_id() {
        return product_id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Timestamp getTime() {
        return bid_time;
    }
}