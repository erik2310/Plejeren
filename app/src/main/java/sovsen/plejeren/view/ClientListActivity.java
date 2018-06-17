package sovsen.plejeren.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import sovsen.plejeren.R;
import sovsen.plejeren.view.presenter.Client;
import sovsen.plejeren.view.presenter.ClientAdapter;

public class ClientListActivity extends AppCompatActivity {

    // Deklarer en ListView variabel
    private ListView mClients_ListView;

    // Deklarer en DatabaseReference variabel
    private DatabaseReference mDatabase;

    private static final String TAG = "ClientListActivity";

    // Deklarer knappen til kalender
    private Button btnCalender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list);

        // final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item);

        // En ArrayList der kan indeholde Client objekter
        final ArrayList<Client> clientArrayList = new ArrayList<>();

        // En custom ArrayAdapter som fungerer sammen med clientArrayList
        final ClientAdapter mAdapter = new ClientAdapter(this, clientArrayList);

        // Sætter ListView med id clients_listview til mClients_ListView
        mClients_ListView = (ListView) findViewById(R.id.clients_listview);

        // Sætter ListView til at bruge ClientAdapter
        mClients_ListView.setAdapter(mAdapter);

        btnCalender = (Button) findViewById(R.id.btnCalender);

        // Henter en instance af FirebaseDatabasens root
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Formaterer en dato så den bliver vist som dag, måned og år
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Laver et Date object
        Date todaysDate = new Date();

        mDatabase.child("Clients").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator iterator = dataSnapshot.getChildren().iterator();
                while (iterator.hasNext()) {
                    DataSnapshot childSnapshot = (DataSnapshot) iterator.next();

                    String name = (String) childSnapshot.child("Name").getValue();
                    String address = (String) childSnapshot.child("Address").getValue();
                    String time = (String) childSnapshot.child("Time").getValue();
                    String date = (String) childSnapshot.child("Date").getValue();

                    if (date.equals(btnCalender.getText().toString())) {
                        clientArrayList.add(new Client(name, address, time));
                    }

                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Intent incomingintent = getIntent();
        String date = incomingintent.getStringExtra("date");
        btnCalender.setText(date);

        // Metode der får dig fra Clientlist, til CalenderActivity og som (passerer intentet).
        btnCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientListActivity.this, CalenderActivity.class);
                startActivity(intent);
            }
        });

        if (btnCalender.getText().toString().equals("")) {
            // Sætter Calender button text til dagens dato
            btnCalender.setText(dateFormat.format(todaysDate));
        }

        // Kører når man trykker på en item i client listview.
        mClients_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView name = (TextView) view.findViewById(R.id.clientname_textView);
                Toast.makeText(ClientListActivity.this, name.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_client_list_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.log_out_button:
                // Logger brugeren ud
                FirebaseAuth.getInstance().signOut();

                // Lukker for activity
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
