package xyz.garrulous.garrulous;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import xyz.garrulous.garrulous.Activities.RegisterActivity;
import xyz.garrulous.garrulous.Model.PrefSingleton;
import xyz.garrulous.garrulous.Requests.Get;
import xyz.garrulous.garrulous.Requests.Post;

public class MainActivity extends AppCompatActivity {

    int duration = Toast.LENGTH_SHORT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        PrefSingleton.getInstance().Initialize(getApplicationContext());
        //setSupportActionBar(toolbar); //comment this out for time being.
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // when user submit information to login this is the method that is checked in order for them
    // to goto there main account.
    public void LoginEventHandler(View view){
        EditText username = (EditText) findViewById(R.id.usernameEditText);
        EditText password = (EditText) findViewById(R.id.passwordEditText);
        if(username.getText().toString().matches("") &&
                password.getText().toString().matches("")){
            Toast toast = Toast.makeText(getApplicationContext(), "Missing Info",
                    duration);
            toast.show();

        } else {
            Intent intent = new Intent(this,GarrulousActivity.class);
            // user name and password to send to API.
            Log.d("username :", username.getText().toString());
            Log.d("password :", password.getText().toString());
            /*
            // This should be POST
            Post p = new Post();
            p.setParam("email", "mike@mike.com"); // setting users input
            p.setParam("password", "pass");
            LoginTask loginTask = new LoginTask();
            intent.putExtra("USER_SESSION", "1234");
            startActivity(intent);
            */
        }
    }

    public void NewUserEventHandler(View view){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    // We are retriving the users token
    // and if error - true display a toast message.
    private class loginTask extends AsyncTask<Get, String, String> {

        @Override
        protected String doInBackground(Get... params) {
            HashMap content = HttpManager.getData(params[0]);
            // user password information is incorrect.
            if(content.get("code").equals("403")){
                return "{ \"error\": username or password not valid.}";
            } else {
                // user name and password is correct than post GET message.
                return String.valueOf(content.get("body"));
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // we see the http response
            Log.d("Results GET", result);
        }
    }

}

