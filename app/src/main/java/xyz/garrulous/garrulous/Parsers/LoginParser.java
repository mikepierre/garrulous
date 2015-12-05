package xyz.garrulous.garrulous.Parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import xyz.garrulous.garrulous.Model.Users;

/**
 * Created by michaelpierre on 11/15/15.
 */
public class LoginParser {

    public static String[] setLoginInfo(String content){
        try{
            // this will be changed when we finalize how we want to autehticate.
            JSONArray json = new JSONArray(content);
            JSONObject obj = json.getJSONObject(0);
            Users users = new Users();
            /*
            users.setEmail(obj.getString("email"));
            String UserEmail = users.getEmail();
            users.setPassword(obj.getString("password"));
            String UserPassword = users.getPassword();

            String UserAutenticationArray[] = {UserEmail,UserPassword};

            return UserAutenticationArray;
            */

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
