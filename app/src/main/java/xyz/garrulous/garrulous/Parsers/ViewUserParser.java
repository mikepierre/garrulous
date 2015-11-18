package xyz.garrulous.garrulous.Parsers;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import xyz.garrulous.garrulous.Model.AboutUsers;
import xyz.garrulous.garrulous.Model.Users;

/**
 * Created by michaelpierre on 11/17/15.
 */
public class ViewUserParser {

    public static HashMap ViewUserProfile(String content, int key){
        try{

            JSONArray json = new JSONArray(content);
            JSONObject obj = json.getJSONObject(0);
            Users users = new Users();
            AboutUsers aboutUsers = new AboutUsers();

            HashMap map = new HashMap<>();

            users.setUid(obj.getInt("uid"));
            users.setFirstName(obj.getString("first_name"));
            users.setLastName("last_name");
            aboutUsers.setAboutMe(obj.getString("about_me"));
            aboutUsers.setLocation(obj.getString("location"));

            int uid = users.getUid();
            String first_name = users.getFirstName();
            String last_name = users.getLastName();
            String about_me = aboutUsers.getAboutMe();
            String location = aboutUsers.getLocation();

            map.put("uid", uid);
            map.put("first_name", first_name);
            map.put("last_name", last_name);
            map.put("about_me", about_me);
            map.put("location", location);

            return map;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
