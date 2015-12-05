package xyz.garrulous.garrulous.model;


import android.content.SharedPreferences;

/**
 * Created by Richard Meyers on 12/5/2015.
 */
public class Token {

    SharedPreferences sharedpreferences;

    public String getAuthToken() {
        String token = sharedpreferences.getString("auth_token", "");
        return token;
    }

    public void setAuthToken(String auth_token) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("auth_token", auth_token);
        editor.commit();
    }


}
