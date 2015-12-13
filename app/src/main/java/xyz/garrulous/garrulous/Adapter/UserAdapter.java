package xyz.garrulous.garrulous.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import xyz.garrulous.garrulous.Model.Users;
import xyz.garrulous.garrulous.R;

/**
 * Created by michaelpierre on 11/14/15.
 */
public class UserAdapter extends ArrayAdapter<Users> {

    private Context context;
    private List<Users> usersList;

    public UserAdapter(Context context, int resource, List<Users> objects){
        super(context, resource, objects);
        this.context = context;
        this.usersList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.users_list, parent, false);
        Users user = usersList.get(position);
        TextView tv = (TextView) view.findViewById(R.id.userNameTextView);
        tv.setText(user.getFirstName() +" "+user.getLastName());
        return view;
    }


}
