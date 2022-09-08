package Tests;

import dao.DBConnection;
import dao.ProductDAO;
import junit.framework.TestCase;
import models.Product;

import java.util.Date;

public class ProductsTest extends TestCase {
    private ProductDAO productDAO;
    @Override
    protected void setUp() throws Exception {
        productDAO = new ProductDAO(DBConnection.getInstance());
    }

    public void testAdd() {
        Product pr1 = new Product(1, 1, "vantuzi", 20, new Date());
        Product pr2 = new Product(2, 1, "vantuzis dzmakaci", 20, new Date());
        productDAO.addProduct(pr1);
        productDAO.addProduct(pr2);
        assertTrue(productDAO.containsProduct("vantuzi"));
        assertTrue(productDAO.containsProduct("vantuzis dzmakaci"));
        assertFalse(productDAO.containsProduct("vantuzis da"));

    }
    public void testGetFromID() {

    }
}
