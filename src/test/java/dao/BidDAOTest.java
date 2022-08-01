package dao;

import models.Bid;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class BidDAOTest extends DAOTest {
    private BidDAO dao;
    protected void setUp() throws SQLException, ClassNotFoundException {
        super.setUp();
        dao = new BidDAO(con);
    }

    public void testInsertGet() throws SQLException {
        UUID dummyUUID = UUID.randomUUID();
        con.createStatement().executeUpdate("INSERT INTO users(id, email) VALUES (unhex(replace('" + dummyUUID + "', '-', '')), 'bla@mail.com')");
        con.createStatement().executeUpdate("INSERT INTO products(id) VALUES (unhex(replace('" + dummyUUID + "', '-', '')))");

        dao.addNewBid(dummyUUID, dummyUUID, new BigDecimal(100.00));
        List<Bid> bids = dao.getAllForUser(dummyUUID);
        List<Bid> bids2 = dao.getAllForProduct(dummyUUID);
        assertTrue(!bids.isEmpty());
        assertEquals(bids.size(), bids.size());
        Bid b = bids.get(0);
        Bid b2 = bids2.get(0);
        assertEquals(100.0, b.getAmount().doubleValue());
        assertEquals(b.getAmount().doubleValue(), b2.getAmount().doubleValue());
        assertEquals(dummyUUID, b.getBidderId());
        assertEquals(b.getBidderId(), b2.getBidderId());
        assertEquals(dummyUUID, b.getProductId());
        assertEquals(b.getProductId(), b2.getProductId());
    }
}
