package xyz.garrulous.garrulous.Requests;


/**
 * Created by michaelpierre on 11/15/15.
 */
public class Post extends Request {

    private String body = "";

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
