package xyz.garrulous.garrulous.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import xyz.garrulous.garrulous.HttpManager;
import xyz.garrulous.garrulous.Parsers.ViewUserParser;
import xyz.garrulous.garrulous.R;
import xyz.garrulous.garrulous.Requests.Get;

public class ViewProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
/*
        Get g = new Get();
        g.setMethod("GET");
        g.setUri("http://10.0.2.2/"); // all users web service
        ViewProfileTask viewProfileTask = new ViewProfileTask();
        */

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

            case R.id.action_view_profile: Intent a = new Intent(this, ViewProfileActivity.class);
                startActivity(a);
                break;

            case R.id.action_edit_profile: Intent b = new Intent(this,EditProfileActivity.class);
                startActivity(b);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public class ViewProfileTask extends AsyncTask<Get, String, String> {

        @Override
        protected String doInBackground(Get... params) {
            String content = HttpManager.getData(params[0]);
            return content;
        }

        @Override
        protected  void onPostExecute(String result) {

            super.onPostExecute(result);

        }
    }


}
