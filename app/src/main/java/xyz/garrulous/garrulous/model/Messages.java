package xyz.garrulous.garrulous.model;
import java.sql.Time;
/**
 * Created by michaelpierre on 11/14/15.
 */
public class Messages {
    int id;
    String message;
    String is_read;
    int sent_datetime;
    int uid_to;
    int uid_from;

    public Messages(int id, String message, String is_read, int sent_datetime,
                    int uid_to, int uid_from) {
        this.id = id;
        this.message = message;
        this.is_read = is_read;
        this.sent_datetime = sent_datetime;
        this.uid_to = uid_to;
        this.uid_from = uid_from;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIs_read() {
        return is_read;
    }

    public void setIs_read(String is_read) {
        this.is_read = is_read;
    }

    public int getSent_datetime() {
        return sent_datetime;
    }

    public void setSent_datetime(int sent_datetime) {
        this.sent_datetime = sent_datetime;
    }

    public int getUid_to() {
        return uid_to;
    }

    public void setUid_to(int uid_to) {
        this.uid_to = uid_to;
    }

    public int getUid_from() {
        return uid_from;
    }

    public void setUid_from(int uid_from) {
        this.uid_from = uid_from;
    }
}
