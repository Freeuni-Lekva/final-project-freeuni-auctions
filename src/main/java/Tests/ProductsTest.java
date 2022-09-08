package Tests;

import dao.DBConnection;
import dao.ProductDAO;
import junit.framework.TestCase;
import models.Product;
import models.Status;

import java.util.Date;
import java.util.List;

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
        assertNull(p.getDescription());
        assertEquals(p.getCategoryId(), 1);
        assertEquals(p.getBidId(), -1);
        assertEquals(p.getCurrPrice(), 20);
        assertEquals(p.getStatus(), Status.available);
        assertNull(p.getImage());
    }

    public void testGetByCategory() {
        Product pr1 = new Product(1, 1, "foo", 20, new Date());
        Product pr2 = new Product(2, 1, "foo bar", 20, new Date());
        Product pr3 = new Product(3, 2, "bar", 20, new Date());
        productDAO.addProduct(pr1);
        productDAO.addProduct(pr2);
        productDAO.addProduct(pr3);
        List<Product> res = productDAO.getProductsByCategory(1);
        assertEquals(5, res.get(0).getId());    // 5 and 6 as IDs are IDs on my sql server.
        assertEquals(6, res.get(1).getId());
    }
}
