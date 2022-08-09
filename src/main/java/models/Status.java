package models;

public class Status {
    private boolean isAvailable;

    public Status(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getStatusString() {
        if (isAvailable) {
            return "available";
        }   else {
            return "sold";
        }
    }
}
