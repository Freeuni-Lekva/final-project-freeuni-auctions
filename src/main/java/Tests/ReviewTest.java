package Tests;

import dao.DBConnection;
import dao.ProductDAO;
import dao.ReviewDAO;
import dao.UserDAO;
import models.Product;
import models.RegularUser;
import models.Review;
import models.User;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import org.testng.asserts.Assertion;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReviewTest {
    @Test
    public void testAdd() throws SQLException, NoSuchAlgorithmException {
        ReviewDAO reviews = new ReviewDAO();
        UserDAO users = new UserDAO();
        users.addUser(new RegularUser(0, "A", "B", "C", "SMTHN", 0));
        users.addUser(new RegularUser(1, "M", "B", "mail", "SMTHN", 0));
        ProductDAO prods = new ProductDAO(DBConnection.getInstance());
        reviews.addReview(new Review(0, 0, 0, 1, "TEXT"));

    }
    @Test
    public void testCorrect(){
        ReviewDAO reviews = new ReviewDAO();
    }
    @Test
    public void testContains(){
        ReviewDAO reviews = new ReviewDAO();

    }
}
