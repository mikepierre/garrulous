package xyz.garrulous.garrulous.Requests;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Request {
    /*
    This is the parent class to all requests. The URI is set here along with the basis for
    HTTP REST calls.
     */
    private String uri = "http://garrulous.xyz/";
    private Map<String, String> params = new HashMap<>();

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public void setParam(String key, String value){
        params.put(key, value);
    }

    public String getEncodedParams(){
        StringBuilder sb = new StringBuilder();
        for (String key : params.keySet()){
            String value = null;
            try {
                value = URLEncoder.encode(params.get(key), "UTF-8");
            } catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }

            if(sb.length() > 0){
                sb.append("&");
            }
            sb.append(key + "=" + value);
        }
        return  sb.toString();
    }
}
