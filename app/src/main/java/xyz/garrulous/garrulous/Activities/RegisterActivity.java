package xyz.garrulous.garrulous.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import xyz.garrulous.garrulous.GarrulousActivity;
import xyz.garrulous.garrulous.HttpManager;
import xyz.garrulous.garrulous.Parsers.UserParser;
import xyz.garrulous.garrulous.R;
import xyz.garrulous.garrulous.Requests.Get;
import xyz.garrulous.garrulous.Requests.Post;

public class RegisterActivity extends AppCompatActivity {

    int duration = Toast.LENGTH_SHORT;
    String first_name, last_name, user_name, password;

    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Activity", "Started Redister Activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }


    // when user clicks register then it will validate to make sure all fields are valid,
    // and if the fields are not valid a toast message will come up.
    // if all fields are valid then we will call POST on v1/user to insert new user data.

    public void registerNewUserHandler(View view) {
        // Get user text inputs.
        EditText first_name = (EditText) findViewById(R.id.firstNameEditTextRegister);
        EditText last_name = (EditText) findViewById(R.id.lastNameEditText);
        EditText user_name = (EditText) findViewById(R.id.usernameEditText);
        EditText password = (EditText) findViewById(R.id.passwordEditText);



        if (first_name.getText().toString().matches("")
                && last_name.getText().toString().matches("")
                && user_name.getText().toString().matches("")
                && password.getText().toString().matches("")) {
            Toast toast = Toast.makeText(getApplicationContext(), "All Fields are required!",
                    duration);
            toast.show();
        } else {
            // get the 200 code from the post request when registering user.
            // if code is equal to 200 then we need to call the get request to
            // retreive the token than we start the Garrulous intent.

            Post post = new Post();

            post.setUrn("v1/user");
            post.setParam("first_name", first_name);
            post.setParam("last_name", "");
            post.setParam("user_name", "");
            post.setParam("password","");




            // Add user to database via webservice.
            // Start Activity and create session
            Intent intent = new Intent(this, GarrulousActivity.class);
            startActivity(intent);
        }

    }

    private class registerTask extends AsyncTask<Post, String, String >{

        // once user clicks register the progress bar will show
        @Override
        protected void onPreExecute(){
            pb.setVisibility(View.VISIBLE);
        }

        // loads content in the background using POST.
        @Override
        protected String doInBackground(Post... params) {
            String content = HttpManager.postData(params[0]);
            return content;
        }

        // once register we log result, and setting progress bar to invincible.
        @Override
        protected void onPostExecute(String result) {
            pb.setVisibility(View.INVISIBLE);
            Log.d("Results", result);

        }

    }

    private class loginTask extends AsyncTask<Get, String, String>{

        @Override
        protected String doInBackground(Get... gets) {
            return null;
        }
    }
}
