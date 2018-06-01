package sovsen.plejeren.view.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sovsen.plejeren.R;

public class ClientAdapter extends ArrayAdapter<Client> {

    // Deklarer context og Arraylist med Clients
    private Context mContext;
    private List<Client> clientList = new ArrayList<>();

    public ClientAdapter(@NonNull Context context, ArrayList<Client> list) {
        super(context, 0, list);
        mContext = context;
        clientList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItem = convertView;

        if (listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);

        Client currentClient = clientList.get(position);

        TextView name = (TextView) listItem.findViewById(R.id.clientname_textView);
        name.setText(currentClient.getmName());

        TextView address = (TextView) listItem.findViewById(R.id.clientaddress_textView);
        address.setText(currentClient.getmAddress());

        TextView time = (TextView) listItem.findViewById(R.id.clienttime_textView);
        time.setText(currentClient.getmTime());

        return listItem;
    }
}
