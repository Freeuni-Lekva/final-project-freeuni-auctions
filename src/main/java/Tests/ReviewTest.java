package Tests;

import dao.*;
import models.Product;
import models.RegularUser;
import models.Review;
import models.User;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReviewTest {
    private final ReviewDAO reviews = new ReviewDAO(DBConnection.getInstance());
    private final UserDAO users = new UserDAO(DBConnection.getInstance());
    private final ProductDAO prods = new ProductDAO(DBConnection.getInstance());
    private final CategoryDAO categories = new CategoryDAO(DBConnection.getInstance());
    @Test
    public void testAdd() throws SQLException, NoSuchAlgorithmException {
        users.addUser(new RegularUser("A", "B", "C", "M", 0));
        users.addUser(new RegularUser("C", "B", "D", "M", 0));
        users.addUser(new RegularUser("G", "B", "G", "M", 0));
        categories.addNewCategory("categ");
        prods.addProduct(new Product(1, categories.getFromName("categ").getId(), "product", 100, new Date(1)));
        Review revSt = new Review(1, 1, 1, 2, "REVIEW TEXT");
        reviews.addReview(revSt);
        Review revFin = reviews.getReviewFromID(1);
        assertTrue(revFin.equals(revSt));
    }
    @Test
    public void getForAccountTest() throws SQLException {
        Review rev2 = new Review(2, 1,1, 2, "ANOTHER REVIEW1");
        Review rev3 = new Review(3, 1,1,3, "ANOTHER REVIEW2");
        reviews.addReview(rev2);
        reviews.addReview(rev3);
        List<Review> ls = reviews.getAllReviewsForAccount(1);
        assertTrue(ls.get(1).equals(rev2));
        assertTrue(ls.get(2).equals(rev3));
    }
    @Test
    public void getForProductTest() throws SQLException {
        Product pr = new Product(1, categories.getFromName("categ").getId(), "product1", 100, new Date(1));
        prods.addProduct(pr);
        Review rev4 = new Review(4,1, pr.getId(), 2, "ANOTHER REVIEW3");
        List<Review> ls = reviews.getAllReviewsForProduct(pr.getId());
        assertTrue(ls.get(1).equals(rev4));
    }
}
