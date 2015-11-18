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
import xyz.garrulous.garrulous.Requests.GetRequest;

public class ViewProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        GetRequest g = new GetRequest();
        g.setMethod("GET");
        g.setUri("http://10.0.2.2/"); // all users web service
        ViewProfileTask viewProfileTask = new ViewProfileTask();
        try {
           String Response = viewProfileTask.execute(g).get();
          ViewUserParser vw = new ViewUserParser();

            HashMap hm = ViewUserParser.ViewUserProfile(Response, 0);
            /*
            hm.get("uid");
            hm.get("first_name");
            hm.get("last_name");
            hm.get("about_me");
            hm.get("location");
            */
            Log.d("Response from vp ac", String.valueOf(hm));

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
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

    public class ViewProfileTask extends AsyncTask<GetRequest, String, String> {

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
