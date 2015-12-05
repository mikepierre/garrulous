package xyz.garrulous.garrulous.Requests;


public class Request {

    // The URL for the web service at production stage
    //private String URL = "http://garrulous.xyz/";

    // The URL for the web service at testing stage.
    private String URL = "http://10.0.2.2:8080/";

    public String getURL(){
        return URL;
    }

}
