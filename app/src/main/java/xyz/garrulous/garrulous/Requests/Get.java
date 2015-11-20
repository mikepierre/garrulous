package xyz.garrulous.garrulous.Requests;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Get extends Request {

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

    public String getEncodedParams() {
        StringBuilder sb = new StringBuilder();
        for(String key: params.keySet()){
            String value = null;
            try {
                value = URLEncoder.encode(params.get(key), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            if(sb.length() > 0){
                sb.append("&");
            }

            sb.append(key + "=" + value);
        }
        return sb.toString();
    }
}
