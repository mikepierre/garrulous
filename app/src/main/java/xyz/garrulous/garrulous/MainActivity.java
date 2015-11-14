package xyz.garrulous.garrulous;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import xyz.garrulous.garrulous.Activities.RegisterActivity;

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

    public void LoginEventHandeler(View view){
        EditText email = (EditText) findViewById(R.id.signInEditText);
        EditText password = (EditText) findViewById(R.id.passwordEditText);
        if(email.getText().toString().matches("") &&
                password.getText().toString().matches("")){
            Toast toast = Toast.makeText(getApplicationContext(), "Missing Info",
                    duration);
            toast.show();

        } else {
            Intent intent = new Intent(this,GarrulousActivity.class);
            startActivity(intent);
        }
    }

    public void NewUserEventHandeler(View view){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
}
