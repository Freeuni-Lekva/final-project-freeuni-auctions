import dao.UserDAO;
import models.RegularUser;
import models.User;
import org.junit.jupiter.api.Test;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

public class UsersTest {
    @Test
    public void testAdd() throws NoSuchAlgorithmException, SQLException {
        UserDAO users = new UserDAO();
        users.addUser(new RegularUser(0, "A", "B", "C", "SMTHN", 0));
        assertTrue(users.containsUsername("A"));
    }
    @Test
    public void testCorrect() throws NoSuchAlgorithmException, SQLException {
        UserDAO users = new UserDAO();
        users.addUser(new RegularUser(0, "B", "B", "D", "SMTHN", 0));
        assertTrue(users.correct("D", User.hashPassword("B")));
    }
    @Test
    public void testContains() throws NoSuchAlgorithmException, SQLException {
        UserDAO users = new UserDAO();
        User us = new RegularUser(0, "X", "B", "X", "SMTHN", 0);
        users.addUser(us);
        us.setId(users.getUserByUsername("X", false).getId());
        assertTrue(users.containsEmail("X"));
        assertTrue(users.containsUsername("X"));
        assertTrue(users.getUserByEmail("X", false).equals(us));
        assertTrue(users.getUserByID(us.getId(), false).equals(us));
    }
    @Test
    public void testInvalidUsers() throws SQLException, NoSuchAlgorithmException {
        UserDAO users = new UserDAO();
        User us = new RegularUser(0, "XA", "B", "XA", "SMTHN", 0);
        users.addUser(us);
        assertFalse(users.isSuspended("XA"));
        users.setSuspended("XA");
        assertTrue(users.isSuspended("XA"));
    }
}
