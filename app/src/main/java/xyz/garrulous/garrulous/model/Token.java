package xyz.garrulous.garrulous.Model;

import android.util.Log;

/**
 * Created by Richard Meyers on 12/5/2015.
 */
public class Token {

    private String message = "";
    private Boolean error  = false;

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
}
