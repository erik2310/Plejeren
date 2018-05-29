package sovsen.plejeren.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import sovsen.plejeren.R;

public class MenuActivity extends AppCompatActivity {

    // Deklarer en Button variabel
    private Button mWorkplan_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Sætter button med id workplan_button til mWorkplan_button
        mWorkplan_button = (Button) findViewById(R.id.workplan_button);

        mWorkplan_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openClientList = new Intent(MenuActivity.this, ClientListActivity.class);
                startActivity(openClientList);
            }
        });
    }
}
