package sovsen.plejeren.view.presenter;

/* Class in test


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

import sovsen.plejeren.view.LoginActivity;
import sovsen.plejeren.view.MenuActivity;

public class LoginPresenter {

    // Deklarer en FirebaseAuth variabel
    private FirebaseAuth mAuth;

    public void initializeFirebaseAuth() {
        // Henter en instance af Firebase Authentication
        mAuth = FirebaseAuth.getInstance();
    }

    public void signIn(String email, String password, final Context mLoginContext, final LoginActivity loginActivity) {

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(loginActivity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Toast.makeText(mLoginContext, "Login fuldført!", Toast.LENGTH_LONG).show();
                    FirebaseUser user = mAuth.getCurrentUser();

                    loginActivity.startMenuActivity();

                    // Lukker for activity
                    loginActivity.finish();

                } else {

                    Toast.makeText(loginActivity, "Login fejlede!", Toast.LENGTH_LONG).show();

                }
            }
        });

    }

}
*/