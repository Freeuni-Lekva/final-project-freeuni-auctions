import dao.DBConnection;
import dao.SaleDAO;
import junit.framework.TestCase;
import models.Sale;
import org.junit.BeforeClass;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SaleTest extends TestCase {
    SaleDAO saleDAO;
    Sale sale1, sale2, sale3, sale4, sale5;
    List<Sale> sales, sales1, sales2;

    {
        saleDAO = new SaleDAO(TestDBConnection.getInstance());

        sale1 = new Sale(1,1,1,Timestamp.valueOf("1983-07-12 21:30:56"),120);
        sale2 = new Sale(2,2,1,Timestamp.valueOf("1983-07-12 21:30:56"),220);
        sale3 = new Sale(3,3,2,Timestamp.valueOf("1983-07-12 21:30:56"),320);
        sale4 = new Sale(4,4,2,Timestamp.valueOf("1983-07-12 21:30:56"),420);
        sale5 = new Sale(5,5,2,Timestamp.valueOf("1983-07-12 21:30:56"),420);

        sales   = new ArrayList<>(Arrays.asList(sale1,sale2,sale3,sale4,sale5));
        sales1  = new ArrayList<>(Arrays.asList(sale1,sale2));
        sales2  = new ArrayList<>(Arrays.asList(sale3,sale4,sale5));
    }

    @BeforeClass
    public static void setUpClass() {
        TestDBConnection.resetDatabase();
    }
    public void testAdd(){
        for (final Sale s : sales){
            saleDAO.addSale(s);
        }
        List<Sale> dbSales = saleDAO.getAll();
        assertEquals(5,dbSales.size());
        assertTrue(sales.containsAll(dbSales));
    }

    public void testGetBySaleId(){
        assertEquals(sale1,saleDAO.getSaleBySaleID(1));
        assertEquals(sale2,saleDAO.getSaleBySaleID(2));
        assertEquals(sale3,saleDAO.getSaleBySaleID(3));
        assertEquals(sale4,saleDAO.getSaleBySaleID(4));
        assertEquals(sale5,saleDAO.getSaleBySaleID(5));
    }

    public void testGetByProductId(){
        assertEquals(sale1,saleDAO.getSaleByProductID(1));
        assertEquals(sale2,saleDAO.getSaleByProductID(2));
        assertEquals(sale3,saleDAO.getSaleByProductID(3));
        assertEquals(sale4,saleDAO.getSaleByProductID(4));
        assertEquals(sale5,saleDAO.getSaleByProductID(5));
    }

    public void testGetByUserId(){
        assertTrue(sales1.containsAll(saleDAO.getSalesByUserID(1)));
        assertTrue(sales2.containsAll(saleDAO.getSalesByUserID(2)));
    }
}
