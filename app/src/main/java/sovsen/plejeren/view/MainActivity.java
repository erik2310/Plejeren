package sovsen.plejeren.view;

import android.Manifest;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import sovsen.plejeren.R;
import sovsen.plejeren.view.model.GPStracker;

public class MainActivity extends AppCompatActivity {

    Button btnLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLoc = (Button) findViewById(R.id.btnGetLoc);
        ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        btnLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GPStracker gt = new GPStracker(getApplicationContext());
                Location l = gt.getLocation();
                if( l == null){
                    Toast.makeText(getApplicationContext(),"GPS unable to get Value",Toast.LENGTH_SHORT).show();
                }else {
                    double lat = l.getLatitude();
                    double lon = l.getLongitude();
                    Date currentTime = Calendar.getInstance().getTime();
                    Toast.makeText(getApplicationContext(),"GPS Lat = "+lat+"\n lon = "+lon+"\n Tidspunkt = "+currentTime,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}