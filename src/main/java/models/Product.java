package models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class Product {
    private UUID id, account_id, category_id, bid_id;
    private String name;
    private String description;
    private BigDecimal currPrice;
    private Status status;
    private Date datePosted;
    private Date endDate;

    // this constructor is used by productDAO only to get a product from a database.
    public Product(UUID id, UUID account_id, UUID category_id, String name,
                   String description, UUID bid_id, BigDecimal currPrice, Status status,
                   Date datePosted, Date endDate) {
        this.id = id;
        this.account_id = account_id;
        this.category_id = category_id;
        this.name = name;
        this.description = description;
        this.bid_id = bid_id;
        this.currPrice = currPrice;
        this.status = status;
        this.datePosted = datePosted;
        this.endDate = endDate;
    }

    // used when creating a new product. CURRENT TIME of creating this constructor gets assigned to datePosted.
    public Product(UUID account_id, UUID category_id, String name, BigDecimal price, Date endDate) {
        this.account_id = account_id;
        this.category_id = category_id;
        this.name = name;
        this.currPrice = price;
        this.status = Status.valueOf("available");
        this.datePosted = new Date();
        this.endDate = endDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public UUID getAccountId() {
        return account_id;
    }

    public UUID getCategoryId() {
        return category_id;
    }

    public UUID getBidId() {
        return bid_id;
    }

    public void setBidId(UUID bid_id) {
        this.bid_id = bid_id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getCurrPrice() {
        return currPrice;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public Date getEndDate() {
        return endDate;
    }
}