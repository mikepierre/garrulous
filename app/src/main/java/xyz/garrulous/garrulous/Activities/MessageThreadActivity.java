package xyz.garrulous.garrulous.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import xyz.garrulous.garrulous.Adapter.MessageAdapter;
import xyz.garrulous.garrulous.HttpManager;
import xyz.garrulous.garrulous.Model.Messages;
import xyz.garrulous.garrulous.Model.PrefSingleton;
import xyz.garrulous.garrulous.Model.Token;
import xyz.garrulous.garrulous.Model.Users;
import xyz.garrulous.garrulous.Parsers.MessageThreadParser;
import xyz.garrulous.garrulous.R;
import xyz.garrulous.garrulous.Requests.Get;
import xyz.garrulous.garrulous.Requests.Post;

public class MessageThreadActivity extends AppCompatActivity {

    Users users = new Users();
    List<Messages> MessageThread;
    List<MessageThreadTask> messageThreadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_thread);

        // get token.
        Token token = new Token();
        //Log.d("Token @ MessageAct: ", token.getSharedToken());

        // This block helps us be verbose about making sure the username and uid are filled
        String username;
        Integer uid;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                username = null;
                uid = null;
            } else {
                username = extras.getString("username");
                uid = extras.getInt("uid");
            }
        } else {
            username = (String) savedInstanceState.getSerializable("username");
            uid = (Integer) savedInstanceState.getSerializable("uid");
        }
        Log.d("username is", username);
        Log.d("uid is", uid.toString());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(username);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_white_24dp);
        setSupportActionBar(toolbar);

        users.setUid(Integer.parseInt(uid.toString()));

        messageThreadTask = new ArrayList<>();
        requestData(token.getSharedToken());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void requestData(String token) {
        MessageThreadTask messageThreadTask = new MessageThreadTask();
        Get g = new Get();
        g.setUrn("/v1/msg");
        g.setParam("token", token);
        g.setParam("to_uid", String.valueOf(users.getUid()));
        Log.d("Tok2API", token);
        Log.d("UID2API", String.valueOf(users.getUid()));
        messageThreadTask.execute(g);
    }

    protected void updateDisplay() {

        Log.d("MessageThread", String.valueOf(MessageThread));
        if (MessageThread != null) {
            MessageAdapter messageAdapter = new MessageAdapter(this, R.layout.message_list, MessageThread);
            final ListView messageThread = (ListView) findViewById(R.id.listView2);
            messageThread.setAdapter(messageAdapter);
        }
    }

    // POST Message to api
    public void sendMessageHandler(View view) throws JSONException {
        EditText message = (EditText) findViewById(R.id.messageText);
        Log.d("Message ", message.getText().toString());
        Log.d("UID->", String.valueOf(users.getUid()));
        Token token = new Token();

        // SEND POST HERE
        Post post = new Post();
        // json['to_id'], json['message'],
        post.setUrn("v1/msg");
        post.setParam("token", token.getSharedToken());

        JSONObject messageData = new JSONObject();
        messageData.put("to_id", String.valueOf(users.getUid()));
        messageData.put("message", message.getText().toString());

        post.setHttpBody(messageData.toString());

        PostMessageTask postMessageTask = new PostMessageTask();

        try {
           String response = postMessageTask.execute(post).get();
            Log.d("JSON: ", response);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        // RESET Message Text after submited.
        message.setText("");
        messageThreadTask = new ArrayList<>();
        requestData(token.getSharedToken());
    }

    private class PostMessageTask extends AsyncTask<Post, String, String> {

        @Override
        protected String doInBackground(Post... params) {
            HashMap content = HttpManager.postData(params[0]);
            // check if any invalid details
            if (content.get("code").equals("403")) {
                return "{ \"error\": Invalid details}";
            } else {
                //  post json message.
                return String.valueOf(content.get("body"));
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d("Result", result);
        }
    }


    private class MessageThreadTask extends AsyncTask<Get, String, String> {
        @Override
        protected void onPreExecute() {

            messageThreadTask.add(this);

        }

        @Override
        protected String doInBackground(Get... params) {
            HashMap content = HttpManager.getData(params[0]);
            // check if any invalid details
            if (content.get("code").equals("403")) {
                return "{ \"error\": \"Invalid details\"}";
            } else {
                //  post json message.
                return String.valueOf(content.get("body"));
            }
        }

        @Override
        protected void onPostExecute(String result) {
            MessageThread = MessageThreadParser.parseMessage(result);
            //Log.d("Message ThreadMSG", String.valueOf(MessageThread.get(0).getMessage()));
            updateDisplay();
            Log.d("Results1", result);
        }
    }

}
