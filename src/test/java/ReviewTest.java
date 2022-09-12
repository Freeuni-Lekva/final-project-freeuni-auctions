import dao.*;
import junit.framework.TestCase;
import models.Product;
import models.RegularUser;
import models.Review;
import models.User;
import org.junit.BeforeClass;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


public class ReviewTest extends TestCase {
    private final ReviewDAO reviews = new ReviewDAO(TestDBConnection.getInstance());
    private final UserDAO users = new UserDAO(TestDBConnection.getInstance());
    private final ProductDAO prods = new ProductDAO(TestDBConnection.getInstance());
    private final CategoryDAO categories = new CategoryDAO(TestDBConnection.getInstance());

    @BeforeClass
    public static void setUpClass() {
        TestDBConnection.resetDatabase();
    }

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

    public void getForAccountTest() throws SQLException {
        Review rev2 = new Review(2, 1,1, 2, "ANOTHER REVIEW1");
        Review rev3 = new Review(3, 1,1,3, "ANOTHER REVIEW2");
        reviews.addReview(rev2);
        reviews.addReview(rev3);
        List<Review> ls = reviews.getAllReviewsForAccount(1);
        assertEquals(rev2, ls.get(2));
        assertEquals(rev3, ls.get(3));
    }

    public void getForProductTest() throws SQLException {
        Product pr = new Product(1, categories.getFromName("categ").getId(), "product1", 100, new Date(1));
        prods.addProduct(pr);
        long pr_id = prods.getProductsByName(pr.getName()).get(0).getId();
        Review rev4 = new Review(4,1, prods.getProductsByName("product1").get(0).getId(), 2, "ANOTHER REVIEW3");
        reviews.addReview(rev4);
        List<Review> ls = reviews.getAllReviewsForProduct(prods.getProductsByName("product1").get(0).getId());
        assertEquals(rev4.getUser_id(), ls.get(0).getUser_id());
        assertEquals(rev4.getProduct_id(), ls.get(0).getProduct_id());
        assertEquals(ls.get(0).getReviewText(), rev4.getReviewText());
    }
}
