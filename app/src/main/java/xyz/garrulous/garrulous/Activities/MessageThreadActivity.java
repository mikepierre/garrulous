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
