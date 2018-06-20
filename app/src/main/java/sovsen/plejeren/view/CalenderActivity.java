package sovsen.plejeren.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CalendarView;

import sovsen.plejeren.R;

// Extender AppCompatActivity, så vi kan bruge dets bibliotek.
public class CalenderActivity extends AppCompatActivity {

    private static final String TAG = "CalenderActivity";
// Laver CalenderView objektet.
    private CalendarView mCalendarView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender_layout);
        //// Deklarer CalenderView objektet.
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                String dayAsString;
                String monthAsString;

                // Sætter et 0 foran tallet i month og gemmer det som String, hvis tallet er fra 0 til 8, ellers gør den det ikke
                if (month <= 8) {
                    monthAsString = "0" + String.valueOf((month + 1));
                } else {
                    monthAsString = String.valueOf((month + 1));
                }

                // Sætter et 0 foran tallet i dayOfMonth og gemmer det som String, hvis tallet er fra 0 til 9, ellers gør den det ikke
                if (dayOfMonth <= 9) {
                    dayAsString = "0" + String.valueOf(dayOfMonth);
                } else {
                    dayAsString = String.valueOf(dayOfMonth);
                }

                String date = dayAsString + "/" + monthAsString + "/" + year;
                // Printer datoen til loggen
                Log.d(TAG, "onSelectedDayChange: dd/mm/yyyy: " + date);
                // Tager dig fra CalenderActivity og tilbage til ClientListActivity når du trykker på en dato og viser den
                Intent intent = new Intent(CalenderActivity.this, ClientListActivity.class);
                //Sætter keyvalue, med navnet date og hvad det er.
                intent.putExtra("date", date);
                startActivity(intent);


            }
        });





    }
}
