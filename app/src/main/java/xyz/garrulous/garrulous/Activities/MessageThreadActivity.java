package xyz.garrulous.garrulous.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;

import xyz.garrulous.garrulous.HttpManager;
import xyz.garrulous.garrulous.R;
import xyz.garrulous.garrulous.Requests.Get;
import xyz.garrulous.garrulous.Requests.Post;

public class MessageThreadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_thread);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Thread");
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_white_24dp);
        setSupportActionBar(toolbar);

        // This block helps us be verbose about making sure the username and uid are filled
        String username;
        Integer uid;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
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

    /*private void requestMessageThread(String token){

        MessageThreadListTask messageThreadListTask = new MessageThreadListTask();
        Get g = new Get();
        g.setUrn("/v1/msg");
        g.setParam("token", token);
        messageThreadListTask.execute(g);
    }*/

    // POST Message to api
    public void sendMessageHandler(View view){
        EditText message = (EditText) findViewById(R.id.messageText);
        // get the id
        Bundle bundle = getIntent().getExtras();
        String uid_string = bundle.getString("uid");
        int uid = Integer.parseInt(uid_string);
    }

    private class postMessageTask extends AsyncTask<Post, String, String>{

        @Override
        protected String doInBackground(Post... params){
            return null;
        }

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            Log.d("Result", result);
        }
    }

}
