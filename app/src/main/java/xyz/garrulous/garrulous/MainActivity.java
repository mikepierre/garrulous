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

import java.util.concurrent.ExecutionException;

import xyz.garrulous.garrulous.Activities.RegisterActivity;
import xyz.garrulous.garrulous.Parsers.LoginParser;
import xyz.garrulous.garrulous.Requests.GetRequest;

public class MainActivity extends AppCompatActivity {

    int duration = Toast.LENGTH_SHORT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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

    public void LoginEventHandler(View view){
        EditText email = (EditText) findViewById(R.id.signInEditText);
        EditText password = (EditText) findViewById(R.id.passwordEditText);
        if(email.getText().toString().matches("") &&
                password.getText().toString().matches("")){
            Toast toast = Toast.makeText(getApplicationContext(), "Missing Info",
                    duration);
            toast.show();

        } else {
            Intent intent = new Intent(this,GarrulousActivity.class);
            GetRequest g = new GetRequest();
            g.setMethod("GET");
            g.setUri("http://10.0.2.2/"); // login
            g.setParam("email", "mike@mike.com"); // setting users input
            g.setParam("password", "pass");
            LoginTask loginTask = new LoginTask();
            try {
                String Response = loginTask.execute(g).get();
                LoginParser loginParser = new LoginParser();

                String[] ResponseArray = loginParser.setLoginInfo(Response); // will change after.
                Log.d("EMAIL", ResponseArray[0]);
                Log.d("Password", ResponseArray[1]);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            intent.putExtra("USER_SESSION", "1234");
            intent.putExtra("USER_EMAIL", email.getText().toString());
            intent.putExtra("FIRST_NAME", "Michael");
            startActivity(intent);
        }
    }

    public void NewUserEventHandeler(View view){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    public class LoginTask extends AsyncTask<GetRequest, String, String>{

        @Override
        protected String doInBackground(GetRequest... params) {
            String content = HttpManager.getData(params[0]);
            return content;
        }

        @Override
        protected  void onPostExecute(String result) {

            super.onPostExecute(result);

        }
    }

}

