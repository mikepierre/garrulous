package xyz.garrulous.garrulous.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xyz.garrulous.garrulous.Adapter.MessageThreadAdapter;
import xyz.garrulous.garrulous.Adapter.UserListAdapter;
import xyz.garrulous.garrulous.HttpManager;
import xyz.garrulous.garrulous.MainActivity;
import xyz.garrulous.garrulous.Model.Messages;
import xyz.garrulous.garrulous.Model.Token;
import xyz.garrulous.garrulous.Model.Users;
import xyz.garrulous.garrulous.Parsers.MessageListParser;
import xyz.garrulous.garrulous.Parsers.ThreadListParser;
import xyz.garrulous.garrulous.R;
import xyz.garrulous.garrulous.Requests.Get;

public class MessagesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    List<Messages> MessageList;
    List<MessageThreadListTask> messageThreadListTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // We don't need this logo in here if there is a logo in the drawer
        //toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MessagesActivity.this, FindUserActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Log.d("Messages", "Opened Messages View");
        Token token = new Token();
        Log.d("Token @ GarrulousAct: ", token.getSharedToken());
        messageThreadListTasks = new ArrayList<>();
        requestMessageThread(token.getSharedToken());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.messages, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Intent intent = new Intent(this, EditProfileActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_logout) {
            Token t = new Token();
            t.setToken("");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void requestMessageThread(String token){

        MessageThreadListTask messageThreadListTask = new MessageThreadListTask();
        Get g = new Get();
        g.setUrn("/v1/msg");
        g.setParam("token", token);
        messageThreadListTask.execute(g);
    }

    protected void updateDisplay(final String result){
        MessageThreadAdapter messageThreadAdapter = new MessageThreadAdapter(this, R.layout.thread_list, MessageList);
        final ListView messageList = (ListView)findViewById(R.id.listView);
        messageList.setAdapter(messageThreadAdapter);


        messageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MessagesActivity.this, MessageThreadActivity.class);

                Object item = messageList.getItemAtPosition(i);
                Messages message = (Messages) item;
                Log.d("UserList", "Selected is " + message.getUser_name_message_from());
                Log.d("UserList", "Selected is " + message.getUid_message_from());
                intent.putExtra("username", message.getUser_name_message_from());
                intent.putExtra("uid", message.getUid_message_from());
                startActivity(intent);
            }
        });
    }


    private class MessageThreadListTask extends AsyncTask<Get, String, String> {

        @Override
        protected void onPreExecute(){
            messageThreadListTasks.add(this);
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
        protected void onPostExecute(String result){
            MessageList = ThreadListParser.parseMessageThread(result);
            updateDisplay(result);
            Log.d("Results", result);
        }
    }
}
