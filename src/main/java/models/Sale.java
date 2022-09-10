package models;

import java.sql.Timestamp;

public class Sale {
    private final long id, product_id, user_id;
    private final Timestamp date;
    private final double price;

    public Sale(long id, long product_id, long user_id, Timestamp date, double price) {
        this.id = id;
        this.product_id = product_id;
        this.user_id = user_id;
        this.date = date;
        this.price = price;
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

    public Timestamp getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", product_id=" + product_id +
                ", user_id=" + user_id +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}