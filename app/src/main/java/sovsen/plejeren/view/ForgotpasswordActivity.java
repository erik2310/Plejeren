package sovsen.plejeren.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import sovsen.plejeren.R;

public class ForgotpasswordActivity extends AppCompatActivity {

    // Deklarer en EditText variabel
    private EditText mEmail_field;

    // Deklarer en Button variabel
    private Button mReset_button;

    // Deklarer en FirebaseAuth variabel
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        // Sætter field med id email_field_view til mEmail_field variabel
        mEmail_field = (EditText) findViewById(R.id.email_field_view);

        // Sætter knappen med id reset_button til mReset_button variabel
        mReset_button = (Button) findViewById(R.id.reset_button);

        // Henter en instance af Firebase Authentication
        mAuth = FirebaseAuth.getInstance();

        mReset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Gemmer email i en String datatype
                String email = mEmail_field.getText().toString();
                try {
                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ForgotpasswordActivity.this, "Vi har sendt dig en e-mail med et nulstillings link!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(ForgotpasswordActivity.this, "Nulstillingen fejlede!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                } catch (IllegalArgumentException ex) {
                    Toast.makeText(ForgotpasswordActivity.this, "Indtast en e-mail!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
