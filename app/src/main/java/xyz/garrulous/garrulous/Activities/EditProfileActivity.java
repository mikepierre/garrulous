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
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import xyz.garrulous.garrulous.GarrulousActivity;
import xyz.garrulous.garrulous.HttpManager;
import xyz.garrulous.garrulous.Model.Token;
import xyz.garrulous.garrulous.Model.Users;
import xyz.garrulous.garrulous.Parsers.EditProfileParser;
import xyz.garrulous.garrulous.Parsers.ThreadListParser;
import xyz.garrulous.garrulous.Parsers.UserParser;
import xyz.garrulous.garrulous.R;
import xyz.garrulous.garrulous.Requests.Get;
import xyz.garrulous.garrulous.Requests.Post;

public class EditProfileActivity extends AppCompatActivity {

    private EditText editFirstName;
    private EditText editLastName;
    private EditText passwordEditText;

    // starts the activity for edit profile.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        editFirstName = (EditText) findViewById(R.id.firstNameEditTextProfile);
        editLastName = (EditText) findViewById(R.id.lastNameEditTextProfile);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Modify Profile");
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_white_24dp);
        setSupportActionBar(toolbar);

        final Token token = new Token();
        getUserData(token);

        Button updateProfileButton = (Button) findViewById(R.id.updateProfileButton);
        updateProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Token token = new Token();
                try {
                    setUserData(token);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
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

    private void setUserData(Token token) throws ExecutionException, InterruptedException {
        EditProfileTask task = new EditProfileTask();
        Post p = new Post();
        p.setUrn("/v1/user");
        p.setParam("token", token.getSharedToken());
        String settings = "";
        p.setHttpBody(settings);
        String responce = task.execute(p).get();
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



    private class EditProfileTask extends AsyncTask<Post, String, String> {

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

            JSONObject resultobj = null;
            try {
                resultobj = new JSONObject(result);
                Log.d("firstname", (String) resultobj.get("first_name"));
                editFirstName.setText((CharSequence) resultobj.get("first_name"));
                editLastName.setText((CharSequence) resultobj.get("last_name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


}
