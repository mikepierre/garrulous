package xyz.garrulous.garrulous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import xyz.garrulous.garrulous.Requests.GetRequest;


/**
 * Created by michaelpierre on 11/1/15.
 */
public class HttpManager {

    // send get request to server
    public static String getData(GetRequest g) {

        BufferedReader reader = null;
        String uri = g.getUri();
                if( g.getMethod().equals("GET")){
                    uri += "?" + g.getEncodedParams();
                }

        try {
            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(g.getMethod());

            StringBuilder sb = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }
}
