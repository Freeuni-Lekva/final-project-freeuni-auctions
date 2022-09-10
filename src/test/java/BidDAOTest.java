import dao.BidDAO;
import dao.DBConnection;
import models.Bid;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class BidDAOTest  {


    private Connection con = TestDBConnection.getInstance();
    private BidDAO dao = new BidDAO(con);

    @Test
    public void testInsertGet() throws SQLException {
        long dummyID = new Random().nextLong();
        assertNotNull(con);
        con.createStatement().executeUpdate("INSERT INTO users(id, username, email) VALUES (" + dummyID + ", 'bla', 'bla@mail.com')");
        con.createStatement().executeUpdate("INSERT INTO categories(id, name) VALUES (" + dummyID + ", 'categ')");
        con.createStatement().executeUpdate("INSERT INTO products(account_id, category_id) VALUES (" + dummyID + ", " + dummyID + ")");

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