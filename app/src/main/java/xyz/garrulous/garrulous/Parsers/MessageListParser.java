package xyz.garrulous.garrulous.Parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import xyz.garrulous.garrulous.Model.Messages;

/**
 * Created by michaelpierre on 12/11/15.
 */
public class MessageListParser {

    public static HashMap MessageListData(String content, int key){
        try {
            JSONArray json = new JSONArray(content);
            JSONObject obj = json.getJSONObject(key);
            Messages messages = new Messages();
            HashMap map = new HashMap<>();

            messages.setUid_message_from(obj.getInt("uid_message_from"));
            messages.setUser_name_message_from(obj.getString("user_name_message_from"));
            int uid_message_from = messages.getUid_message_from();
            String user_name_message_from = messages.getUser_name_message_from();
            map.put("uid_message_from",uid_message_from);
            map.put("user_name_message_from",user_name_message_from);

            return map;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

}
