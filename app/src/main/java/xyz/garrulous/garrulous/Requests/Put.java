package xyz.garrulous.garrulous.Requests;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Put extends Request {

    private String urn;
    private Map<String, String> params = new HashMap<>();

    private String getUri() {
        return this.getURL() + this.getUri();
    }

    public String getUrn() {
        return urn;
    }

    public void setUrn(String urn) {
        this.urn = urn;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    /*
    * jsonPUT
    * creates jSON array to include within the body of PUT request.
    * @return json array.
    * */
    public String jsonPUT() throws JSONException {
        JSONObject JSONobj = new JSONObject();
        JSONArray JSONArray = new JSONArray();
        for (String key : params.keySet()) {
            String value = null;
            try {
                value = URLEncoder.encode(params.get(key), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            JSONobj.put(key, value);
        }
        JSONArray.put(JSONobj);
        return JSONArray.toString();
    }

}
