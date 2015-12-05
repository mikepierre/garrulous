package xyz.garrulous.garrulous.Parsers;

import android.util.Log;

import org.json.JSONObject;

import xyz.garrulous.garrulous.Model.Token;

/**
 * Created by michaelpierre on 11/15/15.
 */

public class LoginParser {

    public static Token setLoginInfo(String server_returned){
        try{
            Token t = new Token();

            JSONObject obj = new JSONObject(server_returned);

            Log.d("Parsed Token",obj.getString("token"));
            Log.d("setLoginInfo:",server_returned);
            t.setToken(obj.getString("token"));
            t.setError(obj.getBoolean("error"));
            t.setMessage(obj.getString("msg"));
            return t;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
