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

import java.util.HashMap;

import xyz.garrulous.garrulous.GarrulousActivity;
import xyz.garrulous.garrulous.HttpManager;
import xyz.garrulous.garrulous.Parsers.EditProfileParser;
import xyz.garrulous.garrulous.R;
import xyz.garrulous.garrulous.Requests.Get;

public class EditProfileActivity extends AppCompatActivity {

    // starts the activity for edit profile.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    // inflates the menu with the menu activity.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        /*if (id == R.id.action_settings) {
            return true;
        }*/

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

}
