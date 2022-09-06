package models;

public class Sale {
    private final long id, product_id, user_id;

    public Sale(long id, long product_id, long user_id) {
        this.id = id;
        this.product_id = product_id;
        this.user_id = user_id;
    }

    public long getId() {
        return id;
    }

    public long getProduct_id() {
        return product_id;
    }

    public long getUser_id() {
        return user_id;
    }
}