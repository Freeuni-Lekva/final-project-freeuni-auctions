package Tests;

import dao.*;
import models.Product;
import models.RegularUser;
import models.Review;
import models.User;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import org.testng.asserts.Assertion;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReviewTest {
    private final ReviewDAO reviews = new ReviewDAO(DBConnection.getInstance());
    private final UserDAO users = new UserDAO(DBConnection.getInstance());
    private final ProductDAO prods = new ProductDAO(DBConnection.getInstance());
    private final CategoryDAO categories = new CategoryDAO(DBConnection.getInstance());
    @Test
    public void testAdd() throws SQLException, NoSuchAlgorithmException {
        users.addUser(new RegularUser(1, "A", "B", "C", "M", 0));
        users.addUser(new RegularUser(2, "C", "B", "D", "M", 0));
        users.addUser(new RegularUser(3, "G", "B", "G", "M", 0));
        categories.addNewCategory("categ");
        prods.addProduct(new Product(1, categories.getFromName("categ").getId(), "product", new BigDecimal(100), new Date(1)));
        Review revSt = new Review(1, 1, 1, 2, "REVIEW TEXT");
        reviews.addReview(revSt);
        Review revFin = reviews.getReviewFromID(1);
        assertTrue(revFin.equals(revSt));
    }
    @Test
    public void testCorrect(){
        
    }
    @Test
    public void testContains(){


    }
}
