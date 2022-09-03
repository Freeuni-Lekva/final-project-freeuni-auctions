package models;

import java.util.UUID;

public class Review{
    private long id, account_id, product_id, costumer_id;
    private String reviewText;

    /*
    poor,
    average,
    good,
    great,
    excellent
    */
    // to get a product from a database
    public Review(long id, long account_id, long product_id, long costumer_id, String reviewText){
        this.id = id;
        this.account_id = account_id;
        this.product_id = product_id;
        this.costumer_id = costumer_id;
        this.reviewText = reviewText;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public void setCostumer_id(long costumer_id) {
        this.costumer_id = costumer_id;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public long getId() {
        return id;
    }

    public long getAccount_id() {
        return account_id;
    }

    public long getProduct_id() {
        return product_id;
    }

    public long getCostumer_id() {
        return costumer_id;
    }

    public String getReviewText() {
        return reviewText;
    }
}
