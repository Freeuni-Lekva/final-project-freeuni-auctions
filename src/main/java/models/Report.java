package models;
import java.util.UUID;

public class Report{
    private long id, reporter_id, reported_id;
    String comment;


    // to get report from table
    public Report(long id, long reporter_id, long reported_id, String comment) {
        this.id = id;
        this.reporter_id = reporter_id;
        this.reported_id = reported_id;
        this.comment = comment;
    }

    public String getComment(){
        return comment;
    }
    public long getReportId() {
        return id;
    }

    public long getReporterId() {
        return reporter_id;
    }

    public long getReportedId() {
        return reported_id;
    }

    public void setComment(String com){
        this.comment = com;
    }
    public void setReportId(long id) {
        this.id = id;
    }

    public void setReporterId(long reporter_id) {
        this.reporter_id = reporter_id;
    }

    public void setReportedId(long reported_id) {
        this.reported_id = reported_id;
    }
}