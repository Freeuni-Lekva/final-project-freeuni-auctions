package Tests;

import dao.DBConnection;
import dao.ProductDAO;
import junit.framework.TestCase;
import models.Product;
import models.Status;

import java.util.Date;

public class ProductsTest extends TestCase {
    private ProductDAO productDAO;
    @Override
    protected void setUp() throws Exception {
        productDAO = new ProductDAO(DBConnection.getInstance());
    }

    public void testAdd() {
        Product pr1 = new Product(1, 1, "foo", 20, new Date());
        Product pr2 = new Product(2, 1, "foo bar", 20, new Date());
        productDAO.addProduct(pr1);
        productDAO.addProduct(pr2);
        assertTrue(productDAO.containsProduct("foo"));
        assertTrue(productDAO.containsProduct("foo bar"));
        assertFalse(productDAO.containsProduct("foo foo"));

    }
    public void testGetFromID() {
        Product p = productDAO.getFromID(5);
        assertEquals(p.getName(), "foo");
        assertEquals(p.getAccountId(), 1);
        assertEquals(p.getDescription(), null);
        assertEquals(p.getCategoryId(), 1);
        assertEquals(p.getBidId(), -1);
        assertEquals(p.getCurrPrice(), 20);
        assertEquals(p.getStatus(), Status.available);
        assertEquals(p.getImage(), null);
    }
}
