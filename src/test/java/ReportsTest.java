import dao.DBConnection;
import dao.ReportDAO;
import dao.UserDAO;
import junit.framework.TestCase;
import models.RegularUser;
import models.Report;
import org.junit.BeforeClass;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;



public class ReportsTest extends TestCase {
    private final ReportDAO reports = new ReportDAO(TestDBConnection.getInstance());
    private final UserDAO users = new UserDAO(TestDBConnection.getInstance());

    @BeforeClass
    public static void setUpClass() {
        TestDBConnection.resetDatabase();
    }

    public void testAdd() throws SQLException, NoSuchAlgorithmException {
        users.addUser(new RegularUser("A", "B", "C", "M", 0));
        users.addUser(new RegularUser("C", "B", "D", "M", 0));
        users.addUser(new RegularUser("G", "B", "G", "M", 0));
        Report rep = new Report(1, 1, 2, "comment about report, why, when, what, how, first", false);
        reports.addReport(rep);
        Report resultRep = reports.getReportFromID(1);
        assertTrue(resultRep.equals(rep));
    }

    public void testGet() throws NoSuchAlgorithmException, SQLException {
        Report rep = new Report(2, 1, 2, "comment about report, why, when, what, how, second", false);
        Report rep2 = new Report(3, 1, 2, "comment about report, why, when, what, how, third", false);
        reports.addReport(rep);
        reports.addReport(rep2);
        List<Report> lst = reports.getAllReportForAccount(2);
        assertTrue(lst.get(2).equals(rep2));
        assertTrue(lst.get(1).equals(rep));
    }

    public void testGetByAccount() throws SQLException {
        Report rep = new Report(4, 1, 2, "comment about report, why, when, what, how, fourth", false);
        Report rep2 = new Report(5, 1, 2, "comment about report, why, when, what, how, fifth", false);
        reports.addReport(rep);
        reports.addReport(rep2);
        List<Report> lst = reports.getAllReportFromAccount(1);
        assertTrue(lst.get(4).equals(rep2));
        assertTrue(lst.get(3).equals(rep));
    }

    public void testGetActive() throws SQLException {
        Report rep = new Report(6, 1, 2, "comment about report, why, when, what, how, fourth", false);
        Report rep2 = new Report(7, 1, 2, "comment about report, why, when, what, how, fifth", false);
        reports.addReport(rep);
        reports.addReport(rep2);
        List<Report> lst = reports.getUnresolved();
        assertTrue(lst.get(6).equals(rep2));
        assertTrue(lst.get(5).equals(rep));
    }
}
