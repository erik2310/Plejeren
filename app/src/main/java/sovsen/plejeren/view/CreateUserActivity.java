package sovsen.plejeren.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import sovsen.plejeren.R;

public class CreateUserActivity extends AppCompatActivity {

    // Deklarer en EditText variabel
    private EditText mEmail_field;
    private EditText mPassword_field;
    private EditText mOne_time_key_field;

    // Deklarer en Button variabel
    private Button mCreate_user_button;

    // Deklarer en FirebaseAuth variabel
    private FirebaseAuth mAuth;

    // Deklarer en DatabaseReference variabel
    private DatabaseReference mDatabase;

    // Deklarer en Long og String variabel
    Long key;

    // Deklarer en List variabel
    List<Object> one_time_keys_map;

    // Deklarer en boolean datatype
    boolean account_created = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        // Sætter field med id email_field_view til mEmail_field variabel
        mEmail_field = (EditText) findViewById(R.id.email_field_view);

        // Sætter field med id password_field_view til mPassword_field variabel
        mPassword_field = (EditText) findViewById(R.id.password_field_view);

        // Sætter field med id one_time_key_field_view til mOne_time_key_field
        mOne_time_key_field = (EditText) findViewById(R.id.one_time_key_field_view);

        // Sætter knappen med id login_button til mLogin_button variabel
        mCreate_user_button = (Button) findViewById(R.id.create_user_button);

        // Henter en instance af Firebase Authentication
        mAuth = FirebaseAuth.getInstance();

        // Henter en instance af FirebaseDatabasens root
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Henter One Time Keys fra databasen
        getOne_Time_Key_Values();

        mCreate_user_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Gemmer key, email og password i en String datatype
                String one_time_key = mOne_time_key_field.getText().toString();
                String email = mEmail_field.getText().toString();
                String password = mPassword_field.getText().toString();

                for (int i = 0; i < one_time_keys_map.size(); i++) {

                    key = (Long) one_time_keys_map.get(i);

                    if (!one_time_key.equals("0")) {
                        if (one_time_key.equals(String.valueOf(key))) {

                            // Sletter værdien når den er brugt
                            deleteValueInOneTimeKeys(i);

                            // Opretter en bruger med createAccount metoden
                            createAccount(email, password);

                            account_created = true;
                        }

                    }
                }

                // Kører hvis kontoen ikke blev oprettet
                if (!account_created) {
                    Toast.makeText(CreateUserActivity.this, "Engangsnøglen er forkert!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null)
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    private void createAccount(String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(CreateUserActivity.this, "Brugeren blev oprettet!", Toast.LENGTH_LONG).show();
                    FirebaseUser user = mAuth.getCurrentUser();

                    // Åbner menuen efter brugeren er oprettet
                    Intent openMenu = new Intent(CreateUserActivity.this, MenuActivity.class);
                    startActivity(openMenu);

                    // Lukker for activity
                    finish();
                } else {
                    Toast.makeText(CreateUserActivity.this, "Brugeren kunne ikke oprettes!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void getOne_Time_Key_Values() {
        try {

            DatabaseReference getKey = mDatabase.child("One Time Keys");
            getKey.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    one_time_keys_map = (List<Object>) dataSnapshot.getValue();

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                    Toast.makeText(CreateUserActivity.this, "Kunne ikke få forbindelse til databasen!", Toast.LENGTH_LONG).show();

                }
            });
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    private void deleteValueInOneTimeKeys(int i) {
        mDatabase.child("One Time Keys").child(String.valueOf(i)).setValue(0);
    }
}
