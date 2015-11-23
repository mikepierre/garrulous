package xyz.garrulous.garrulous;

import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import xyz.garrulous.garrulous.Requests.Get;
import xyz.garrulous.garrulous.Requests.Post;
import xyz.garrulous.garrulous.Requests.Put;


public class HttpManager {

     String HTTP_STATUS;

    // Request via HTTP GET from the server.
    public static String getData(Get g){
        BufferedReader reader = null;
        String uri = g.getUri();
        uri += "?" + g.getEncodedParams();

        try {
            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            StringBuilder sb = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            return sb.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Request via HTTP POST to the server.

    public static String postData(Post p){
        String uri = p.getUri();
        BufferedReader reader = null;

        try {
            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());

            try {
                writer.write(p.JsonPOST());
                Log.d("JSON", p.JsonPOST());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            writer.flush();

            int responseCode = con.getResponseCode();
            Log.d("Response Code POST", String.valueOf(responseCode));

            StringBuilder sb = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            return sb.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Request via HTTP PUT from the server.

    public static String putData(Put p)
    {
        String uri = p.getUri();
        BufferedReader reader = null;
        try {
            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());

            try {
                writer.write(p.JsonPUT());
                Log.d("JSON", p.JsonPUT());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            writer.flush();

            int responseCode = con.getResponseCode();
            Log.d("Response Status PUT", String.valueOf(responseCode));

            StringBuilder sb = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            return sb.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
    // Get http status for POST, GET, PUT, DELETE.
    public void getHTTPStatus(){

    }
}
