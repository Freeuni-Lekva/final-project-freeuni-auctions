import dao.CategoryDAO;
import junit.framework.TestCase;
import models.Category;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.fail;


public class CategoryDAOTest extends TestCase {
    private final CategoryDAO categoryDAO = new CategoryDAO(TestDBConnection.getInstance());

    @BeforeClass
    public static void setUpClass() {
        TestDBConnection.resetDatabase();
    }

    public void testInsertGet() {
        categoryDAO.addNewCategory("cat1");
        long id = categoryDAO.getFromName("cat1").getId();
        String name = categoryDAO.getFromID(id).getName();
        assertEquals("cat1", name);
        categoryDAO.addNewCategory("cat2");
        Category c = categoryDAO.getFromName("cat2");
        assertNotNull(c);
        assertEquals("cat2", c.getName());
    }

    public void negativeTest() {
        try {
            Category c = categoryDAO.getFromName("asdasd");
            c.getId();
            fail();
        } catch (Exception e) {}

        try {
            Category c = categoryDAO.getFromID(0);
            c.getName();
            fail();
        } catch (Exception e) {}

    }

}
