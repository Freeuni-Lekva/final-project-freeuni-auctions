package dao;
import models.Report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ReportDAO extends DAO{
    private static final String TABLE_NAME = "reports";
    public static final String ATTRIBUTE = "ReportDAO";
    private Connection conn;
    public ReportDAO(Connection conn){
        this.conn = conn;
    }
    public Report getReportFromID(long id) throws SQLException {
        PreparedStatement stm = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE id = ?");
        stm.setLong(1, id);
        ResultSet rs = stm.executeQuery();
        if(rs.next()){
            Report rep = createReport(rs);
            stm.close();
            return rep;
        } else {
            stm.close();
            return null;
        }
    }
    public List<Report> getAllReportForAccount(long reported_id) throws SQLException {
        List<Report> res = new ArrayList<Report>();
        PreparedStatement stm = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE reported_id = ?");
        stm.setLong(1, reported_id);
        ResultSet rs = stm.executeQuery();
        while(rs.next()){
            res.add(createReport(rs));
        }
        stm.close();
        return res;
    }
    public List<Report> getAllReportFromAccount(long reporter_id) throws SQLException {
        List<Report> res = new ArrayList<Report>();
        PreparedStatement stm = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE reporter_id = ?");
        stm.setLong(1, reporter_id);
        ResultSet rs = stm.executeQuery();
        while(rs.next()){
            res.add(createReport(rs));
        }
        stm.close();
        return res;
    }

    private Report createReport(ResultSet rs) throws SQLException {
        long id = Long.parseLong(rs.getString("id"));
        long reporter_id = Long.parseLong(rs.getString("reporter_id"));
        long reported_id = Long.parseLong(rs.getString("reported_id"));
        String comment = rs.getString("comment");
        return new Report(id, reporter_id, reported_id, comment);
    }

    public void addReport(Report rep) throws SQLException {
        PreparedStatement stm = conn.prepareStatement("INSERT INTO " + TABLE_NAME +
                " (reporter_id, reported_id, comment) VALUES ( ?, ?, ? )");
        stm.setLong(1, rep.getReporterId());
        stm.setLong(2, rep.getReportedId());
        stm.setString(3, rep.getComment());
        stm.executeUpdate();
        stm.close();
    }
}
