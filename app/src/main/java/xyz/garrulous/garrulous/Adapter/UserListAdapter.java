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
 * Created by remso on 12/11/2015.
 */
public class UserListAdapter extends ArrayAdapter<Users> {
    private Context context;
    private List<Users> usersList;

    public UserListAdapter(Context context, int resource, List<Users> objects){
        super(context, resource, objects);
        this.context = context;
        this.usersList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.user_list, parent, false);
        Users users = usersList.get(position);
        TextView usernametext = (TextView) view.findViewById(R.id.username);
        TextView firstnametext = (TextView) view.findViewById(R.id.firstname);
        TextView lastnametext = (TextView) view.findViewById(R.id.lastname);
        usernametext.setText(users.getUsername());
        firstnametext.setText(users.getFirstName());
        lastnametext.setText(users.getLastName());

        return view;
    }
}
