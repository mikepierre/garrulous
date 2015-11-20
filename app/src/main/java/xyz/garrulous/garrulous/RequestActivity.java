package xyz.garrulous.garrulous;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.concurrent.ExecutionException;

import xyz.garrulous.garrulous.Requests.Get;
import xyz.garrulous.garrulous.Requests.Post;

public class RequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // ignore the floating action bar
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Test GET AND POST REQUEST HERE.

        Get get = new Get();
        // Setting the URN in order to create the URI
        get.setUrn("v1/auth");
        get.setParam("username", "rickeyrem");
        get.setParam("password", "blahblah");
        getTask getTask = new getTask();

        try {
            // creating a string to get the requested data from GET.
            String request_data = getTask.execute(get).get();
            Log.d("Data recieved GET:",request_data);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }



        Post post = new Post();
        post.setUrn("v1/user");
/*
        post.setParam("username","rickeyrem");
        post.setParam("password", "blahblah");
        post.setParam("email", "prince@rich.com");
        postTask postTask = new postTask();

        // creating a string to get the requested data from POST.
        try {
           String request_data = postTask.execute(post).get();
            Log.d("Data recieved POST:", request_data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
*/

    }

    // Set up AyncTask for get
    private class getTask extends AsyncTask<Get, String, String>{
        // We are executing getData method from httpManager
        @Override
        protected String doInBackground(Get... params) {
            String content = HttpManager.getData(params[0]);
            return content;
        }

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            // we see the http response
            Log.d("Results GET", result);
        }

    }

    private class postTask extends AsyncTask<Post, String, String>{
        @Override
        protected String doInBackground(Post... params) {
            String content = HttpManager.postData(params[0]);
            return content;
        }

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            // we see the http response
            Log.d("Results POST", result);
        }
    }



}
