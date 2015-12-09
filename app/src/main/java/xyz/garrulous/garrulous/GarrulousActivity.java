package xyz.garrulous.garrulous;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xyz.garrulous.garrulous.Activities.EditProfileActivity;
import xyz.garrulous.garrulous.Activities.ViewProfileActivity;
import xyz.garrulous.garrulous.Adapter.MessageThreadAdapter;
import xyz.garrulous.garrulous.Adapter.UserAdapter;
import xyz.garrulous.garrulous.Model.Messages;
import xyz.garrulous.garrulous.Model.PrefSingleton;
import xyz.garrulous.garrulous.Model.Token;
import xyz.garrulous.garrulous.Model.Users;
import xyz.garrulous.garrulous.Parsers.ThreadListParser;
import xyz.garrulous.garrulous.Parsers.UserParser;
import xyz.garrulous.garrulous.Requests.Get;


public class GarrulousActivity extends AppCompatActivity {

    List<Messages> MessageList;
    List<MessageThreadListTask> messageThreadListTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garrulous);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); //need to adjust this.
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
        Log.d("New Activity Garrulous", "Started!");
        PrefSingleton.getInstance().Initialize(getApplicationContext());
        Token token = new Token();
        Log.d("Token @ GarrulousAct: ", token.getSharedToken());
        messageThreadListTasks = new ArrayList<>();
        requestData(token.getSharedToken());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        /*if (id == R.id.action_settings) {
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }
    public boolean ViewSettingsEventHandler(MenuItem item){

        switch (item.getItemId()){

            case R.id.action_edit_profile:
                Intent edit_profile = new Intent(this, EditProfileActivity.class);
                startActivity(edit_profile);
                break;

            case R.id.action_home_screen:
                Intent home_screen = new Intent(this, GarrulousActivity.class);
                startActivity(home_screen);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void requestData(String token){

        MessageThreadListTask messageThreadListTask = new MessageThreadListTask();
        Get g = new Get();
        g.setUrn("/v1/msg");
        g.setParam("token",token);
        messageThreadListTask.execute(g);
    }

    protected  void updateDisplay(){
        MessageThreadAdapter messageThreadAdapter = new MessageThreadAdapter(this, R.layout.thread_list, MessageList);
        final ListView messageList = (ListView)findViewById(R.id.listView);
        messageList.setAdapter(messageThreadAdapter);

        messageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(GarrulousActivity.this, ViewProfileActivity.class);
                startActivity(intent);
            }
        });
    }


    private class MessageThreadListTask extends AsyncTask<Get, String, String>{

        @Override
        protected void onPreExecute(){
            messageThreadListTasks.add(this);
        }

        @Override
        protected String doInBackground(Get... params) {
            HashMap content = HttpManager.getData(params[0]);
            // check if any invalid details
            if (content.get("code").equals("403")) {
                return "{ \"error\": Invalid details}";
            } else {
                //  post json message.
                return String.valueOf(content.get("body"));
            }
        }

        @Override
        protected void onPostExecute(String result){
            MessageList = ThreadListParser.parseMessageThread(result);
            updateDisplay();
            Log.d("Results", result);
        }
    }
}
