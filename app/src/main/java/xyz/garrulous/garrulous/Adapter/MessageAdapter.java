package xyz.garrulous.garrulous.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

import xyz.garrulous.garrulous.Model.Messages;
import xyz.garrulous.garrulous.R;

/**
 * Created by michaelpierre on 12/12/15.
 */
public class MessageAdapter extends ArrayAdapter<Messages> {

    private Context context;
    private List<Messages> messagesList;

    public MessageAdapter(Context context, int resource, List<Messages> objects){
        super(context, resource, objects);
        this.context = context;
        this.messagesList = objects;
    }
//messageFromTextView messageFromTextView
@Override
public View getView(int position, View convertView, ViewGroup parent){
    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    View view = inflater.inflate(R.layout.message_list, parent, false);
    Messages messages = messagesList.get(position);

    Log.d("Messages",messages.getMessage());

    TextView tvUserName = (TextView) view.findViewById(R.id.messageFromTextView);
    tvUserName.setText(messages.getUser_name_message_from()+":");

    TextView tvMessage = (TextView) view.findViewById(R.id.userMessageTextView);
    tvMessage.setText(messages.getMessage());

    return view;
}
}
