package xyz.garrulous.garrulous.AsyncTasks;

import android.os.AsyncTask;

import xyz.garrulous.garrulous.HttpManager;
import xyz.garrulous.garrulous.Requests.GetRequest;

/**
 * Created by michaelpierre on 11/14/15.
 */
public class AllUserTasks extends AsyncTask<GetRequest, String, String> {
    @Override
    protected String doInBackground(GetRequest... params) {
        String content = HttpManager.getData(params[0]);
        return content;
    }

}
