package xyz.garrulous.garrulous.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import xyz.garrulous.garrulous.GarrulousActivity;
import xyz.garrulous.garrulous.HttpManager;
import xyz.garrulous.garrulous.Model.Token;
import xyz.garrulous.garrulous.Model.Users;
import xyz.garrulous.garrulous.Parsers.EditProfileParser;
import xyz.garrulous.garrulous.Parsers.ThreadListParser;
import xyz.garrulous.garrulous.Parsers.UserParser;
import xyz.garrulous.garrulous.R;
import xyz.garrulous.garrulous.Requests.Get;

public class EditProfileActivity extends AppCompatActivity {

    private EditText editFirstName;
    private EditText editLastName;

    // starts the activity for edit profile.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Modify Profile");
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_white_24dp);
        setSupportActionBar(toolbar);
        Token token = new Token();
        editFirstName = (EditText) findViewById(R.id.firstname);
        editLastName = (EditText) findViewById(R.id.firstname);

        getUserData(token);
    }

    // inflates the menu with the menu activity.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void getUserData(Token token) {
        GetUserDataTask task = new GetUserDataTask();
        Get g = new Get();
        g.setUrn("/v1/user");
        g.setParam("token", token.getSharedToken());
        g.setParam("user", token.getUid().toString());
        task.execute(g);
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

    // adds menu item to the infladed menu.
    public boolean ViewSettingsEventHandler(MenuItem item) {

        switch (item.getItemId()) {

            /*case R.id.action_edit_profile:
                Intent edit_profile = new Intent(this, EditProfileActivity.class);
                startActivity(edit_profile);
                break;

            case R.id.action_home_screen:
                Intent home_screen = new Intent(this, GarrulousActivity.class);
                startActivity(home_screen);
                break;*/
        }

        return super.onOptionsItemSelected(item);
    }


    public class EditProfileTask extends AsyncTask<Get, String, String> {

        @Override
        protected String doInBackground(Get... params) {
            HashMap content = HttpManager.getData(params[0]);
            return String.valueOf(content);
        }

        @Override
        protected void onPostExecute(String result) {

            super.onPostExecute(result);

        }
    }

    private class GetUserDataTask extends AsyncTask<Get, String, String> {

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
            Log.d("Results", result);
            UserParser userParser = new UserParser();
            JSONArray json = new JSONArray();
            JSONObject resultobj = null;
            try {
                resultobj = new JSONObject(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            json.put(resultobj);
            List<Users> users = userParser.parseUsers("[" + result.toString() + "]");
            Users user = users.get(0);
            editFirstName.setText(user.getFirstName().toString());
            editLastName.setText(user.getLastName().toString());
        }
    }

}
