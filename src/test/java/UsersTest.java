import dao.UserDAO;
import junit.framework.TestCase;
import models.RegularUser;
import models.User;
import org.junit.BeforeClass;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class UsersTest extends TestCase {
    @BeforeClass
    public static void setUpClass() {
        TestDBConnection.resetDatabase();
    }

    public void testAdd() throws NoSuchAlgorithmException, SQLException {
        UserDAO users = new UserDAO(TestDBConnection.getInstance());
        users.addUser(new RegularUser(3, "A", "B", "C", "SMTHN", 0, false));
        assertTrue(users.containsUsername("A"));
    }

    public void testCorrect() throws NoSuchAlgorithmException, SQLException {
        UserDAO users = new UserDAO(TestDBConnection.getInstance());
        users.addUser(new RegularUser(2, "B", "B", "D", "SMTHN", 0, false));
        assertTrue(users.correct("D", User.hashPassword("B")));
    }

    public void testContains() throws NoSuchAlgorithmException, SQLException {
        UserDAO users = new UserDAO(TestDBConnection.getInstance());
        User us = new RegularUser(0, "X", "B", "X", "SMTHN", 0, false);
        users.addUser(us);
        us.setId(users.getUserByUsername("X", false).getId());
        assertTrue(users.containsEmail("X"));
        assertTrue(users.containsUsername("X"));
        assertTrue(users.getUserByEmail("X", false).equals(us));
        assertTrue(users.getUserByID(us.getId(), false).equals(us));
    }

    public void testInvalidUsers() throws SQLException, NoSuchAlgorithmException {
        UserDAO users = new UserDAO(TestDBConnection.getInstance());
        TestDBConnection.resetDatabase();
        User us = new RegularUser("XA", "B", "XA", "SMTHN", 0);
        users.addUser(us);
        assertFalse(users.isSuspended("XA"));
        users.setSuspended("XA");
        assertTrue(users.isSuspended("XA"));
    }
}
