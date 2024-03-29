import dao.BidDAO;
import dao.DBConnection;
import junit.framework.TestCase;
import models.Bid;
import org.junit.BeforeClass;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class BidDAOTest extends TestCase {
    private Connection con = TestDBConnection.getInstance();
    private BidDAO dao = new BidDAO(con);

    @BeforeClass
    public static void setUpClass() {
        TestDBConnection.resetDatabase();
    }

    public void testInsertGet() throws SQLException {
        long dummyID = new Random().nextLong();
        assertNotNull(con);
        con.createStatement().executeUpdate("INSERT INTO users(id, username, email) VALUES (" + dummyID + ", 'bla', 'bla@mail.com')");
        con.createStatement().executeUpdate("INSERT INTO categories(id, name) VALUES (" + dummyID + ", 'categ')");
        con.createStatement().executeUpdate("INSERT INTO products(user_id, category_id) VALUES (" + dummyID + ", " + dummyID + ")");

        dao.addNewBid(dummyID, dummyID, new BigDecimal(100.00));
        List<Bid> bids = dao.getAllForUser(dummyID);
        List<Bid> bids2 = dao.getAllForProduct(dummyID);
        assertTrue(!bids.isEmpty());
        assertEquals(bids.size(), bids.size());
        Bid b = bids.get(0);
        Bid b2 = bids2.get(0);
        assertEquals(100.0, b.getAmount().doubleValue());
        assertEquals(b.getAmount().doubleValue(), b2.getAmount().doubleValue());
        assertEquals(dummyID, b.getBidderId());
        assertEquals(b.getBidderId(), b2.getBidderId());
        assertEquals(dummyID, b.getItemId());
        assertEquals(b.getItemId(), b2.getItemId());
    }
}