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
    private Product pr1;
    private Product pr2;
    private Product pr3;
    private Product pr4;

    @Override
    protected void setUp() throws Exception {
        productDAO = new ProductDAO(DBConnection.getInstance());
        pr1 = new Product(1, 1, "foo", 20, new Date());
        pr2 = new Product(2, 1, "foo bar", 20, new Date());
        pr3 = new Product(3, 2, "bar", 20, new Date());
        pr4 = new Product(2, 3, "boo", 30, new Date());
        pr4.setStatus(Status.sold);

    }

    public void testAdd() {
        productDAO.addProduct(pr1);
        productDAO.addProduct(pr2);
        productDAO.addProduct(pr3);
        productDAO.addProduct(pr4);
        assertTrue(productDAO.containsProduct("foo"));
        assertTrue(productDAO.containsProduct("foo bar"));
        assertTrue(productDAO.containsProduct("bar"));
        assertTrue(productDAO.containsProduct("boo"));
        assertFalse(productDAO.containsProduct("foo foo"));

    }
    public void testGetFromID() {
        Product p = productDAO.getFromID(5);
        assertEquals(p.getName(), "foo");
        assertEquals(p.getUserId(), 1);
        assertNull(p.getDescription());
        assertEquals(p.getCategoryId(), 1);
        assertEquals(p.getBidId(), -1);
        assertEquals(p.getCurrPrice(), 20);
        assertEquals(p.getStatus(), Status.available);
        assertNull(p.getImage());
    }

    public void testGetByCategory() {
        List<Product> res = productDAO.getProductsByCategory(1);
        assertEquals(5, res.get(0).getId());    // 5 and 6 as IDs are IDs on my sql server.
        assertEquals(6, res.get(1).getId());
    }
    public void testGetByUser() {
        List<Product> res = productDAO.getProductsByUser(2);
        assertEquals("foo", res.get(0).getName());
        assertEquals("foo bar", res.get(1).getName());
    }

    public void testGetByStatus() {
        List<Product> res = productDAO.getProductsByStatus(Status.available);
        assertEquals("foo bar", res.get(0).getName());
        assertEquals("boo", res.get(1).getName());
        assertEquals("bar", res.get(2).getName());
        assertEquals("boo", res.get(3).getName());

    }

}
