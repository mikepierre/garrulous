package xyz.garrulous.garrulous.AsyncTasks;

import android.os.AsyncTask;

import xyz.garrulous.garrulous.HttpManager;
import xyz.garrulous.garrulous.Requests.Get;

/**
 * Created by michaelpierre on 11/15/15.
 */
public class RegisterTasks extends AsyncTask<Get, String, String>{

    @Override
    protected String doInBackground(Get... params) {
        String content = HttpManager.getData(params[0]);
        return content;
    }
}
