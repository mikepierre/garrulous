package xyz.garrulous.garrulous;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import xyz.garrulous.garrulous.Activities.MessagesActivity;
import xyz.garrulous.garrulous.Activities.RegisterActivity;
import xyz.garrulous.garrulous.Model.PrefSingleton;
import xyz.garrulous.garrulous.Model.Token;
import xyz.garrulous.garrulous.Parsers.LoginParser;
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
        Token token = new Token();
        Log.d("Shared Tok: ", token.getSharedToken());
        //setSupportActionBar(toolbar); //comment this out for time being.
        String SharedToken = token.getSharedToken();
        if(SharedToken != ""){
            Log.d("If Shared new intent", "Exists!");
            Intent intent = new Intent(this, MessagesActivity.class);
            startActivity(intent);
        }

        // We have to set this text here because we can't underline stuff in the layout.
        // It has to be done in the code.
        TextView registerTextView = (TextView) findViewById(R.id.registerTextView);
        String udata = "Register Now!";
        SpannableString content = new SpannableString(udata);
        content.setSpan(new UnderlineSpan(), 0, udata.length(), 0);
        registerTextView.setText(content);
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
        /*if (id == R.id.action_settings) {
            return true;
        }*/

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
            try {
                Intent intent = new Intent(this,MessagesActivity.class);
                // user name and password to send to API.
                Log.d("username :", username.getText().toString());
                Log.d("password :", password.getText().toString());

                LoginParser lparser = new LoginParser();

                Get get = new Get();
                get.setUrn("v1/auth");
                get.setParam("username", username.getText().toString());
                get.setParam("password", password.getText().toString());
                loginTask loginTaskObj = new loginTask();
                String loginTaskResponse = loginTaskObj.execute(get).get();
                Log.d("Token Response: ", loginTaskResponse);


                Token token = lparser.setLoginInfo(loginTaskResponse);
                startActivity(intent);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public void NewUserEventHandler(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    // We are retriving the users token
    // and if error - true display a toast message.
    private class loginTask extends AsyncTask<Get, String, String> {

        @Override
        protected String doInBackground(Get... params) {
            HashMap content = HttpManager.getData(params[0]);
            // user password information is incorrect.
            if (content.get("code").equals("403")) {
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

