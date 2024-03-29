package sovsen.plejeren.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import sovsen.plejeren.R;

public class LoginActivity extends AppCompatActivity {

    // Deklarer en EditText variabel
    private EditText mEmail_field;
    private EditText mPassword_field;

    // Deklarer en TextView variabel
    private TextView mForgot_password_view;
    private TextView mCreate_user_view;

    // Deklarer en Button variabel
    private Button mLogin_button;

    // Deklarer en FirebaseAuth variabel
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Sætter field med id email_field_view til mEmail_field variabel
        mEmail_field = (EditText) findViewById(R.id.email_field_view);

        // Sætter field med id password_field_view til mPassword_field variabel
        mPassword_field = (EditText) findViewById(R.id.password_field_view);

        // Sætter view med id forgot_password_view til mForgot_password_view variabel
        mForgot_password_view = (TextView) findViewById(R.id.forgot_password_view);

        // Sætter view med id create_user_view til mCreate_user_view variabel
        mCreate_user_view = (TextView) findViewById(R.id.create_user_view);

        // Sætter knappen med id login_button til mLogin_button variabel
        mLogin_button = (Button) findViewById(R.id.login_button);

        // Henter en instance af Firebase Authentication
        mAuth = FirebaseAuth.getInstance();

        mLogin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Gemmer email og password i en String datatype
                String email = mEmail_field.getText().toString();
                String password = mPassword_field.getText().toString();

                try {
                    // Logger ind med e-mail og password
                    signIn(email, password);
                } catch (IllegalArgumentException ex) {
                    Toast.makeText(LoginActivity.this, "Indtast en e-mail og/eller password!", Toast.LENGTH_LONG).show();
                }
            }
        });

        mForgot_password_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openForgotpassword = new Intent(LoginActivity.this, ForgotpasswordActivity.class);
                startActivity(openForgotpassword);
            }
        });

        mCreate_user_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openCreateUser = new Intent(LoginActivity.this, CreateUserActivity.class);
                startActivity(openCreateUser);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null)
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    // Metode til at logge ind
    public void signIn(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Login fuldført!", Toast.LENGTH_LONG).show();
                    FirebaseUser user = mAuth.getCurrentUser();

                    // Åbner menuen efter brugeren er oprettet
                    Intent openMenu = new Intent(LoginActivity.this, MenuActivity.class);
                    startActivity(openMenu);

                    // Lukker for activity
                    finish();

                } else {

                    Toast.makeText(LoginActivity.this, "Forkert e-mail eller password!\n" + "Ellers tjek din internet forbindelse!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    /*  Lavet til et andet formål

    public void startMenuActivity() {
        // Åbner menuen efter brugeren er oprettet
        Intent openMenu = new Intent(LoginActivity.this, MenuActivity.class);
        startActivity(openMenu);
    }
*/

}
