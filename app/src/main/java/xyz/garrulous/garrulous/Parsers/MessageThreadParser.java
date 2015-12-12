package xyz.garrulous.garrulous.Parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import xyz.garrulous.garrulous.Model.Messages;
import xyz.garrulous.garrulous.Model.Users;

/**
 * Created by michaelpierre on 12/12/15.
 */
public class MessageThreadParser {
////[{"uid_message_from": 1, "user_name_message_from": "rickyrem",
// "uid_message_to": 2, "user_name_message_to": "mike", "message": "hello mike!", "is_read": "0", "date_time": 1449349227}]

    public static List<Messages> parseMessage(String content) {
        try {
            JSONArray json = new JSONArray(content);
            List<Messages> messagesList = new ArrayList<>();
            for (int i = 0; i < json.length(); i++) {
                JSONObject obj = json.getJSONObject(i);
                Messages messages = new Messages();
                messages.setUid_message_from(obj.getInt("uid_message_from"));
                messages.setUser_name_message_from(obj.getString("uid_message_from"));
                messages.setUid_message_to(obj.getInt("uid_message_to"));
                messages.setMessage(obj.getString("message"));
                messages.setDate_time(obj.getInt("date_time"));
                messagesList.add(messages);
            }
            return messagesList;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }


}
