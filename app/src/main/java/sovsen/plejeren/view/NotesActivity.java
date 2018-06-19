package sovsen.plejeren.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import sovsen.plejeren.R;


public class NotesActivity extends AppCompatActivity {
    String uid;
    //  DatabaseReference myRef;
    private Button BtnSave_button;
    // Deklarer en Database Reference variabel
    private DatabaseReference mDatabase;
    String email;
    EditText edt2;
    String st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        BtnSave_button = (Button) findViewById(R.id.BtnSave);

        //To retreive the Data from Sharepreferences

 /*       SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String st1 = prefs.getString("st", st);
        edt2.setText(st1);
*/
        try {
            BtnSave_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    st = edt2.getText().toString();
                    //To save the data
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(NotesActivity.this);
                    SharedPreferences.Editor editor = prefs.edit();

                    editor.putString("st", st);
                    editor.apply();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();

            // Henter en instance af FirebaseDatabasen
            mDatabase = FirebaseDatabase.getInstance().getReference();

            final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

/*
        if (user != null) {
            for (UserInfo profile : user.getProviderData()) {
                // UID specific to the provider
                uid = profile.getUid();
            }
        }
*/

            if (user != null) {
                // User is signed in
                String email = user.getEmail();
            } else {
                // No user is signed in
            }
            Toast.makeText(getApplicationContext(), "User Email: " + user.getEmail(), Toast.LENGTH_SHORT).show();

/*        mGem_button = findViewById(R.id.gem);
        // Sætter button med id gem til mGem_button
        mGem_button.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                mDatabase = database.getReference();

                // Gemmer NotesActivity
                mDatabase.child("Notes").push().child("User:").setValue("Email: ",user.getEmail()+ " Noter: ");
            }

       final ValueEventListener notes = mDatabase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Get Note object and use the values to update
                for (DataSnapshot notesSnapshot : dataSnapshot.getChildren()) {
                    DataSnapshot childsnapshot = notesSnapshot.child("Notes");
                    String notes = (String) childsnapshot.getValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        });*/
        }
    }
}
