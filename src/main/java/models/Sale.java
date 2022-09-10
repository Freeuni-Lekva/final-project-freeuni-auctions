package models;

import java.sql.Timestamp;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return id == sale.id && product_id == sale.product_id && user_id == sale.user_id && Double.compare(sale.price, price) == 0 && date.equals(sale.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product_id, user_id, date, price);
    }
}