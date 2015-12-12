package xyz.garrulous.garrulous.Parsers;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import xyz.garrulous.garrulous.Model.Messages;

/**
 * Created by michaelpierre on 12/5/15.
 */
public class ThreadListParser {

    public static List<Messages> parseMessageThread(String content){
        try{
            //JSONArray json = new JSONArray(content);
            List<Messages> messagelist = new ArrayList<>();
            JSONObject json = new JSONObject(content);
            /*for(int i = 0; i < json.length(); i++){
                JSONObject obj = json.getJSONObject(i);
                Messages message = new Messages();
                message.setUser_name_message_from(obj.getString("user_name_message_from"));
                message.setUid_message_from(obj.getInt("uid_message_from"));
                messagelist.add(message);
            }*/
            for(Iterator iterator = json.keys(); iterator.hasNext();) {
                String key = (String) iterator.next();
                Messages message = new Messages();
                JSONObject user_data = new JSONObject(json.get(key).toString());
                message.setUser_name_message_from(user_data.getString("user_name"));
                message.setUid_message_from(Integer.parseInt(key));
                message.setDate_time(user_data.getInt("date_time"));
                messagelist.add(message);
            }
            return messagelist;
        } catch(JSONException e){
            e.printStackTrace();
            return null;
        }
    }
}
