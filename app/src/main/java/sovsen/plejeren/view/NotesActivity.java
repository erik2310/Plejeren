package sovsen.plejeren.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import sovsen.plejeren.R;


public class NotesActivity extends AppCompatActivity {
    String uid;
//  DatabaseReference myRef;
    private Button mGem_button;
    // Deklarer en Database Reference variabel
    private DatabaseReference mDatabase;
    String email;
    String editText;
    String Notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);


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
        Toast.makeText(getApplicationContext(), "User Email: "+user.getEmail(), Toast.LENGTH_SHORT).show();

        // Sætter button med id gem til mGem_button
        mGem_button = findViewById(R.id.gem);
        mGem_button.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                mDatabase = database.getReference();

                // Gemmer NotesActivity
                mDatabase.child("Notes").push().child("User:").setValue("Email: ",user.getEmail()+ " Noter: ");
            }

 /*       final ValueEventListener notes = mDatabase.addValueEventListener(new ValueEventListener() {

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
        });*/
        });
    }
}
