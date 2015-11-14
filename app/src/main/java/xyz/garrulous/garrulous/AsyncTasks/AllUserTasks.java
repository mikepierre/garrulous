package xyz.garrulous.garrulous.AsyncTasks;

import android.os.AsyncTask;

import xyz.garrulous.garrulous.HttpManager;

/**
 * Created by michaelpierre on 11/14/15.
 */
public class AllUserTasks extends AsyncTask<String, String, String> {
    @Override
    protected String doInBackground(String... params) {
        String content = HttpManager.getData(params[0]);
        return content;
    }


}
