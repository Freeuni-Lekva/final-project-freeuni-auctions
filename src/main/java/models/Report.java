package models;
import java.util.Objects;
import java.util.UUID;

public class Report{
    private long id, reporter_id, reported_id;
    private String comment;

    private boolean resolved;
    // to get report from table
    public Report(long id, long reporter_id, long reported_id, String comment, boolean resolved) {
        this.id = id;
        this.reporter_id = reporter_id;
        this.reported_id = reported_id;
        this.comment = comment;
        this.resolved = resolved;
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

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }

    public boolean isResolved() {
        return resolved;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", reporter_id=" + reporter_id +
                ", reported_id=" + reported_id +
                ", comment='" + comment + '\'' +
                ", resolved=" + resolved +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return id == report.id && reporter_id == report.reporter_id && reported_id == report.reported_id && resolved == report.resolved && Objects.equals(comment, report.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reporter_id, reported_id, comment, resolved);
    }
}
