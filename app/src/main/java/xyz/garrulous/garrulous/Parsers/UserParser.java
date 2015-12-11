package xyz.garrulous.garrulous.Parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import xyz.garrulous.garrulous.Model.Users;


public class UserParser {
    public static List<Users> parseUsers(String content){
        try{
            JSONArray json = new JSONArray(content);
            List<Users> usersList = new ArrayList<>();

            for(int i = 0; i < json.length(); i++){
                JSONObject obj = json.getJSONObject(i);
                Users users = new Users();
                users.setUid(obj.getInt("uid"));
                users.setUsername(obj.getString("username"));
                users.setFirstName(obj.getString("first_name"));
                users.setLastName(obj.getString("last_name"));
                usersList.add(users);
            }
            return usersList;
        } catch(JSONException e){
            e.printStackTrace();
            return null;
        }
    }
}
