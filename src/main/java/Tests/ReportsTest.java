package Tests;

import dao.DBConnection;
import dao.ReportDAO;
import dao.UserDAO;
import models.RegularUser;
import models.Report;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;



public class ReportsTest {
    private final ReportDAO reports = new ReportDAO(DBConnection.getInstance());
    private final UserDAO users = new UserDAO(DBConnection.getInstance());
    @Test
    public void addTest() throws SQLException, NoSuchAlgorithmException {
        users.addUser(new RegularUser(1, "A", "B", "C", "M", 0));
        users.addUser(new RegularUser(2, "C", "B", "D", "M", 0));
        users.addUser(new RegularUser(3, "G", "B", "G", "M", 0));
        Report rep = new Report(1, 1, 2, "comment about report, why, when, what, how");
        reports.addReport(rep);
        Report resultRep = reports.getReportFromID(1);
        assertTrue(resultRep.equals(rep));
    }
    @Test
    public void getTest(){

    }
    @Test
    public void getByAccountTest(){

    }
}
