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

        dao.addNewBid(dummyUUID, dummyUUID, new BigDecimal(100.0));
        List<Bid> bids = dao.getAllForUser(dummyUUID);
        assertTrue(!bids.isEmpty());
        Bid b = bids.get(0);
        assertEquals(new BigDecimal(100.0), b.getAmount());
        assertEquals(dummyUUID, b.getId());
    }
}
