package sovsen.plejeren.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import sovsen.plejeren.R;

public class ClientListActivity extends AppCompatActivity {

    // Deklarer en ListView variabel
    private ListView mClients_ListView;

    // Deklarer en DatabaseReference variabel
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item);

        // Sætter ListView med id clients_listview til mClients_ListView
        mClients_ListView = (ListView) findViewById(R.id.clients_listview);
        mClients_ListView.setAdapter(arrayAdapter);

        // Henter en instance af FirebaseDatabasens root
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("Clients").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String myChildValues = (String) dataSnapshot.getValue();
                arrayAdapter.add(myChildValues);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
