package xyz.garrulous.garrulous.Activities;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import xyz.garrulous.garrulous.Adapter.UserListAdapter;
import xyz.garrulous.garrulous.HttpManager;
import xyz.garrulous.garrulous.Model.Token;
import xyz.garrulous.garrulous.Model.Users;
import xyz.garrulous.garrulous.Parsers.UserParser;
import xyz.garrulous.garrulous.R;
import xyz.garrulous.garrulous.Requests.Get;

public class FindUserActivity extends AppCompatActivity {

    List<Users> UserList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Find Someone");
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_white_24dp);
        setSupportActionBar(toolbar);

        Button goButton = (Button) findViewById(R.id.goButton);
        final EditText searchEditText = (EditText) findViewById(R.id.searchEditText);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = searchEditText.getText().toString();
                Log.d("Find", "Looking for user " + user);
                FindSomeoneTask findTask = new FindSomeoneTask();
                Token token = new Token();
                Get g = new Get();
                g.setUrn("/v1/user");
                g.setParam("token", token.getSharedToken());
                g.setParam("user", user);
                findTask.execute(g);
            }
        });

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
    private class FindSomeoneTask extends AsyncTask<Get, String, String> {

        /*@Override
        protected void onPreExecute(){
            messageThreadListTasks.add(this);
        }*/

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
            UserList = UserParser.parseUsers(result);
            updateDisplay();
            Log.d("Results", result);
        }
    }

    protected void updateDisplay(){
        UserListAdapter userListAdapter = new UserListAdapter(this, R.layout.users_list, UserList);
        final ListView userList = (ListView) findViewById(R.id.userListView);
        userList.setAdapter(userListAdapter);

        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("UserList", "item was pressed");
                Object item = userList.getItemAtPosition(i);
                Users user = (Users) item;
                Log.d("UserList", "Selected is " + user.getUsername());


                AlertDialog dialog = new AlertDialog.Builder(view.getContext()).create();
                dialog.setTitle("Create Message");
                dialog.setMessage("Send Message to " + user.getUsername() + "?");
                dialog.setCancelable(false);
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int buttonId) {
                        Toast.makeText(FindUserActivity.this, "yes", Toast.LENGTH_LONG).show();
                    }
                });
                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int buttonId) {
                        Toast.makeText(FindUserActivity.this, "no", Toast.LENGTH_LONG).show();
                    }
                });
                dialog.setIcon(android.R.drawable.ic_dialog_alert);
                dialog.show();
            }
        });
    }

}
