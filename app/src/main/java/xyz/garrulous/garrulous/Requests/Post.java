package xyz.garrulous.garrulous.Requests;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Post extends Request {

    private String urn;
    private Map<String, String> params = new HashMap<>();

    public String getUri(){
        return this.getURL() + this.getUrn();
    }

    public String getUrn() {
        return urn;
    }

    public void setUrn(String urn) {
        this.urn = urn;
    }

    public Map<String, String> getParams(){
        return params;
    }

    public void setParams(Map<String, String> params){
        this.params = params;
    }

    public void setParam(String key, String value){
        params.put(key, value);
    }

    // creates json array and encodes the string.
    public String jsonParams() throws JSONException {
        JSONObject JSONobj = new JSONObject();
        JSONArray JSONArray = new JSONArray();
        for (String key: params.keySet()){
            String value = null;
            try {
                value = URLEncoder.encode(params.get(key), "UTF-8");
            } catch(UnsupportedEncodingException e){
                e.printStackTrace();
            }
            JSONobj.put(key, value);
        }
        JSONArray.put(JSONobj);
        return JSONArray.toString();
    }

}
