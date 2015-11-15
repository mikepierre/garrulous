package xyz.garrulous.garrulous.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import xyz.garrulous.garrulous.AsyncTasks.RegisterTasks;
import xyz.garrulous.garrulous.GarrulousActivity;
import xyz.garrulous.garrulous.R;
import xyz.garrulous.garrulous.Requests.GetRequest;

public class RegisterActivity extends AppCompatActivity {

    int duration = Toast.LENGTH_SHORT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }

    public void regsiterNewUserHandler(View view){
        // Get user text inputs.
        EditText FirstName = (EditText) findViewById(R.id.firstNameEditTextRegister);
        EditText LastName = (EditText) findViewById(R.id.lastNameEditText);
        EditText Email = (EditText) findViewById(R.id.emailEditText);
        EditText Password = (EditText) findViewById(R.id.passwordEditText);

        if(FirstName.getText().toString().matches("")
                && LastName.getText().toString().matches("")
                && Email.getText().toString().matches("")
                && Password.getText().toString().matches("")){
            Toast toast =  Toast.makeText(getApplicationContext(), "All Fields are required!",
                    duration);
            toast.show();
        } else {
            // Add user to database via webservice.
            // Start Activity and create session

            GetRequest g = new GetRequest();
            g.setMethod("GET");
            g.setUri("http://10.0.2.2/ga/register");
            g.setParam("first_name", "First Name");
            g.setParam("last_name", "Last Name");
            g.setParam("email","Email");
            g.setParam("password","password");

            RegisterTasks registerTask = new RegisterTasks();
            registerTask.execute(g);

            Intent intent = new Intent(this,GarrulousActivity.class);
            startActivity(intent);
        }

    }


}
