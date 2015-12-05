package xyz.garrulous.garrulous.Model;


import android.content.SharedPreferences;

/**
 * Created by Richard Meyers on 12/5/2015.
 */
public class Token {

    SharedPreferences sharedpreferences;
    private String message = "";
    private Boolean error  = false;

    public String getSharedToken() {
        String token = sharedpreferences.getString("auth_token", "");
        return token;
    }

    public void setSharedToken(String auth_token) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("auth_token", auth_token);
        editor.commit();
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
