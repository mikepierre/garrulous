package xyz.garrulous.garrulous.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import xyz.garrulous.garrulous.AsyncTasks.RegisterTasks;
import xyz.garrulous.garrulous.GarrulousActivity;
import xyz.garrulous.garrulous.R;
import xyz.garrulous.garrulous.Requests.Get;

public class RegisterActivity extends AppCompatActivity {

    int duration = Toast.LENGTH_SHORT;
    EditText FirstName, LastName, Email, Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }


    // when user clicks register then it will validate to make sure all fields are valid,
    // and if the fields are not valid a toast message will come up.
    // if all fields are valid then we will call POST on v1/user to insert new user data.

    public void registerNewUserHandler(View view) {
        // Get user text inputs.
        EditText FirstName = (EditText) findViewById(R.id.firstNameEditTextRegister);
        EditText LastName = (EditText) findViewById(R.id.lastNameEditText);
        EditText Email = (EditText) findViewById(R.id.emailEditText);
        EditText Password = (EditText) findViewById(R.id.passwordEditText);

        if (FirstName.getText().toString().matches("")
                && LastName.getText().toString().matches("")
                && Email.getText().toString().matches("")
                && Password.getText().toString().matches("")) {
            Toast toast = Toast.makeText(getApplicationContext(), "All Fields are required!",
                    duration);
            toast.show();
        } else {
            // Add user to database via webservice.
            // Start Activity and create session
            Intent intent = new Intent(this, GarrulousActivity.class);
            startActivity(intent);
        }

    }


}
