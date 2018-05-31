package sovsen.plejeren.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

import sovsen.plejeren.R;
import sovsen.plejeren.activities.ClientComponents.Client;
import sovsen.plejeren.activities.ClientComponents.ClientAdapter;

public class ClientListActivity extends AppCompatActivity {

    // Deklarer en ListView variabel
    private ListView mClients_ListView;

    // Deklarer en DatabaseReference variabel
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list);

        // final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item);

        final ArrayList<Client> clientArrayList = new ArrayList<>();
        final ClientAdapter mAdapter = new ClientAdapter(this, clientArrayList);

        // Sætter ListView med id clients_listview til mClients_ListView
        mClients_ListView = (ListView) findViewById(R.id.clients_listview);
        mClients_ListView.setAdapter(mAdapter);

        // Henter en instance af FirebaseDatabasens root
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("Clients").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator iterator = dataSnapshot.getChildren().iterator();
                while (iterator.hasNext()) {
                    DataSnapshot childSnapshot = (DataSnapshot) iterator.next();

                    String name = (String) childSnapshot.child("Name").getValue();
                    String address = (String) childSnapshot.child("Address").getValue();
                    String time = (String) childSnapshot.child("Time").getValue();

                    clientArrayList.add(new Client(name, address, time));

                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
