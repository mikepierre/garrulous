package xyz.garrulous.garrulous.Parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import xyz.garrulous.garrulous.Model.Messages;

/**
 * Created by michaelpierre on 12/5/15.
 */
public class ThreadListParser {

    public static List<Messages> parseMessageThread(String content){
        try{
            JSONArray json = new JSONArray(content);
            List<Messages> messagelist = new ArrayList<>();

            for(int i = 0; i < json.length(); i++){
                JSONObject obj = json.getJSONObject(i);
                Messages message = new Messages();
                message.setUser_name_message_from(obj.getString("user_name_message_from"));
                messagelist.add(message);
            }
            return messagelist;
        } catch(JSONException e){
            e.printStackTrace();
            return null;
        }
    }
}
