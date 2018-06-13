package sovsen.plejeren.view;

import android.Manifest;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

import sovsen.plejeren.R;
import sovsen.plejeren.view.model.GPStracker;

public class MainActivity extends AppCompatActivity {

    Button btnLoc;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLoc = findViewById(R.id.btnGetLoc);
        ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        btnLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GPStracker gt = new GPStracker(getApplicationContext());
                Location l = gt.getLocation();
                DatabaseReference myRef;
                if (l == null) {
                    Toast.makeText(getApplicationContext(), "GPS unable to get Value", Toast.LENGTH_SHORT).show();
                } else {
                    double lat = l.getLatitude();
                    double lon = l.getLongitude();
                    Date currentTime = Calendar.getInstance().getTime();
                    Toast.makeText(getApplicationContext(), "GPS Lat = " + lat + "\n lon = " + lon + "\n Tidspunkt = " + currentTime, Toast.LENGTH_SHORT).show();

                    // Write TimeStamp to the database
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    myRef = database.getReference();

                    myRef.child("Timestamp").child("email").push().setValue("GPS Lat = " + lat + "\n lon = " + lon + "\n Tidspunkt = " + currentTime, Toast.LENGTH_SHORT);


                    }
                }
        });
    }
}
