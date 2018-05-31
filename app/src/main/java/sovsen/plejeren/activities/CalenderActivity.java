package sovsen.plejeren.activities;

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

                String date = dayOfMonth + "/" + (month +1) +"/" + year;
                Log.d(TAG,"onSelectedDayChange: dd/mm/yyyy: "+ date);
                Intent intent = new Intent(CalenderActivity.this,ClientListActivity.class);
                intent.putExtra("date",date);
                startActivity(intent);


            }
        });





    }
}
