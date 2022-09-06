package models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class Product {
    private long id, account_id, category_id, bid_id;
    private String name;
    private String description;
    private BigDecimal currPrice;
    private Status status;
    private Date datePosted;
    private Date endDate;

    // this constructor is used by productDAO only to get a product from a database.
    public Product(long id, long account_id, long category_id, String name,
                   String description, long bid_id, BigDecimal currPrice, Status status,
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
    public Product(long account_id, long category_id, String name, BigDecimal price, Date endDate) {
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

    public long getId() {
        return id;
    }

    public long getAccountId() {
        return account_id;
    }

    public long getCategoryId() {
        return category_id;
    }

    public long getBidId() {
        return bid_id;
    }

    public void setBidId(long bid_id) {
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