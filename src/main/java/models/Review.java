package models;

import java.util.Objects;
import java.util.UUID;

public class Review{
    private long id, user_id, product_id, costumer_id;
    private String reviewText;


    // to get a product from a database
    public Review(long id, long user_id, long product_id, long costumer_id, String reviewText){
        this.id = id;
        this.user_id = user_id;
        this.product_id = product_id;
        this.costumer_id = costumer_id;
        this.reviewText = reviewText;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return id == review.id && user_id == review.user_id && product_id == review.product_id && costumer_id == review.costumer_id && reviewText.equals(review.reviewText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user_id, product_id, costumer_id, reviewText);
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAccount_id(long user_id) {
        this.user_id = user_id;
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

    public long getUser_id() {
        return user_id;
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
