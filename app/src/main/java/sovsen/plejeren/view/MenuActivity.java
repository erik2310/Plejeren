package sovsen.plejeren.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import sovsen.plejeren.R;

public class MenuActivity extends AppCompatActivity {

    // Deklarer en Button variabel
    private Button mWorkplan_button;
    private Button mTime_stamping_button;

    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        button2 = (Button) findViewById(R.id.button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWorkplan();
            }
        });


        // Sætter button med id workplan_button til mWorkplan_button
        mWorkplan_button = (Button) findViewById(R.id.workplan_button);

        mWorkplan_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Åbner ClientListActivity
                Intent openClientList = new Intent(MenuActivity.this, ClientListActivity.class);
                startActivity(openClientList);
            }
        });
        // Sætter button med id time_stamping_button til mTime_stamping_button
        mTime_stamping_button = (Button) findViewById(R.id.time_stamping_button);

        mTime_stamping_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Åbner MainActivity
                Intent openMain = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(openMain);
            }
        });
    }

    private void openWorkplan() {
        Intent intent = new Intent(this, WorkplanActivity.class);
        startActivity(intent);
    }


}
