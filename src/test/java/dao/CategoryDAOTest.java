package dao;

import models.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CategoryDAOTest extends DAOTest {
    CategoryDAO dao;
    protected void setUp() throws SQLException, ClassNotFoundException {
        super.setUp();
        dao = new CategoryDAO(con);
    }

    public void testInsertGet() throws SQLException {
        dao.addNewCategory("cat");
        ResultSet rs = con.createStatement().executeQuery("SELECT id FROM categories WHERE name = 'cat'");
        rs.next();
        UUID id = DAO.binaryToUUID(rs.getBytes(1));
        Category cat = dao.getFromID(id);
        assertEquals("cat", cat.getName());
        assertEquals(id, cat.getId());
    }
}
