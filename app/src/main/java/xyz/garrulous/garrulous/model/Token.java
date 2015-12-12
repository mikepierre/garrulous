package xyz.garrulous.garrulous.Model;

import android.util.Log;

/**
 * Created by Richard Meyers on 12/5/2015.
 */
public class Token {

    private String message = "";
    private Boolean error  = false;
    private Integer uid = 0;
    private String username = "";

    public String getSharedToken() {
        String token = PrefSingleton.getInstance().getPreference("auth_token");
        Log.d("Returning Token: ", token);
        return token;
    }

    public void setSharedToken(String auth_token) {
        PrefSingleton.getInstance().writePreference("auth_token", auth_token);
        Log.d("Setting Token:", auth_token);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getToken() {
        return this.getSharedToken();
    }

    public void setToken(String token) {
        this.setSharedToken(token);
    }

    public Integer getUid() {
        Integer uid = Integer.parseInt(PrefSingleton.getInstance().getPreference("user_name"));
        return uid;
    }

    public void setUid(Integer uid) {
        PrefSingleton.getInstance().writePreference("uid", uid.toString());
        this.uid = uid;
    }

    public String getUsername() {
        String username = PrefSingleton.getInstance().getPreference("user_name");
        return username;
    }

    public void setUsername(String username) {
        PrefSingleton.getInstance().writePreference("user_name", username);
        this.username = username;
    }
}

