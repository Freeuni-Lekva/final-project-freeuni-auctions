package models;

import java.util.Date;

public class Product {
    private long id, user_id, category_id, bid_id;
    private String name;
    private String description;
    private long currPrice;
    private Status status;
    private Date datePosted;
    private Date endDate;
    private String image;
    public static final String ATTRIBUTE = "product";

    // this constructor is used by productDAO only to get a product from a database.
    public Product(long id, long user_id, long category_id, String name,
                   String description, long bid_id, long currPrice, Status status,
                   Date datePosted, Date endDate, String image) {

        this.id = id;
        this.user_id = user_id;
        this.category_id = category_id;
        this.name = name;
        this.description = description;
        this.bid_id = bid_id;
        this.currPrice = currPrice;
        this.status = status;
        this.datePosted = datePosted;
        this.endDate = endDate;
        this.image = image;
    }

    // used when creating a new product. CURRENT TIME of creating this constructor gets assigned to datePosted.
    public Product(long user_id, long category_id, String name, long price, Date endDate) {
        this.user_id = user_id;
        this.category_id = category_id;
        this.bid_id = -1;
        this.name = name;
        this.currPrice = price;
        this.status = Status.valueOf("available");
        this.datePosted = new Date();
        this.endDate = endDate;
        this.description = null;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getId() {
        return id;
    }


    public long getUserId() {
        return user_id;
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

    public long getCurrPrice() {
        return currPrice;
    }

    public String getImage() {
        return image;
    }

    public void setPrice(long amount) {
        currPrice = amount;
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