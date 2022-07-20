package models;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

public class Bid {
    private final UUID id, bidder_id, product_id;
    private final BigDecimal amount;
    private final Date time;

    public Bid(UUID id, UUID bidder_id, UUID product_id, BigDecimal amount, Date time) {
        this.id = id;
        this.bidder_id = bidder_id;
        this.product_id = product_id;
        this.amount = amount;
        this.time = time;
    }

    public UUID getId() {
        return id;
    }

    public UUID getBidder_id() {
        return bidder_id;
    }

    public UUID getItem_id() {
        return product_id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Date getTime() {
        return time;
    }
}