package xyz.garrulous.garrulous.Model;


public class Messages {

    int uid_message_from;
    int uid_message_to;
    String message;
    String is_read;
    String user_name_message_from;
    int date_time;

    public int getUid_message_from() {
        return uid_message_from;
    }

    public void setUid_message_from(int uid_message_from) {
        this.uid_message_from = uid_message_from;
    }

    public int getUid_message_to() {
        return uid_message_to;
    }

    public void setUid_message_to(int uid_message_to) {
        this.uid_message_to = uid_message_to;
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

    public String getUser_name_message_from() {
        return user_name_message_from;
    }

    public void setUser_name_message_from(String user_name_message_from) {
        this.user_name_message_from = user_name_message_from;
    }

    public int getDate_time() {
        return date_time;
    }

    public void setDate_time(int date_time) {
        this.date_time = date_time;
    }
}
