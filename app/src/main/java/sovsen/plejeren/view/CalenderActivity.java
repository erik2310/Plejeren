package sovsen.plejeren.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CalendarView;

import sovsen.plejeren.R;

public class CalenderActivity extends AppCompatActivity {

    private static final String TAG = "CalenderActivity";

    private CalendarView mCalendarView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender_layout);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                String dayAsString;
                String monthAsString;

                if (month <= 8) {
                    monthAsString = "0" + String.valueOf((month + 1));
                } else {
                    monthAsString = String.valueOf((month + 1));
                }

                if (dayOfMonth <= 9) {
                    dayAsString = "0" + String.valueOf(dayOfMonth);
                } else {
                    dayAsString = String.valueOf(dayOfMonth);
                }

                String date = dayAsString + "/" + monthAsString + "/" + year;
                Log.d(TAG, "onSelectedDayChange: dd/mm/yyyy: " + date);
                Intent intent = new Intent(CalenderActivity.this, ClientListActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);


            }
        });





    }
}
