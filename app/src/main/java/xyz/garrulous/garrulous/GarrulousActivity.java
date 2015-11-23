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
import java.util.List;

import xyz.garrulous.garrulous.Activities.EditProfileActivity;
import xyz.garrulous.garrulous.Activities.ViewProfileActivity;
import xyz.garrulous.garrulous.Adapter.UserAdapter;
import xyz.garrulous.garrulous.Model.Users;
import xyz.garrulous.garrulous.Parsers.UserParser;
import xyz.garrulous.garrulous.Requests.Get;


public class GarrulousActivity extends AppCompatActivity {

    List<Users> usersList;
    //List<UserListTask> userListTasks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garrulous);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); //need to adjust this.
        setSupportActionBar(toolbar);
        Log.d("New Activity Garrulous", "Started");
        //userListTasks = new ArrayList<>();
        //requestData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
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

    /*
    private void requestData(){
        UserListTask userListTask = new UserListTask();
        Get g = new Get();
        userListTask.execute(g);
    }


    protected void updateDisplay(){
        UserAdapter userAdapter = new UserAdapter(this, R.layout.users_list, usersList);
        final ListView userList = (ListView)findViewById(R.id.listView);
        userList.setAdapter(userAdapter);

        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(GarrulousActivity.this,ViewProfileActivity.class);
                intent.putExtra("pos",i);
                startActivity(intent);

                Log.d("Log on click", "Clicked"+ i);
                finish();
            }
        });

    }
    */

    // user list task is gets the list of users to put into list view.
    /*

    private class UserListTask extends AsyncTask<Get, String, String>{
        @Override
        protected void onPreExecute() {
            userListTasks.add(this);
        }
        @Override
        protected String doInBackground(Get... params) {
            String content = HttpManager.getData(params[0]);
            return content;
        }
        @Override
        protected void onPostExecute(String result) {

            usersList = UserParser.parseUsers(result);
            Log.d("Results" , result);
            updateDisplay();
        }

    }
    */
}
