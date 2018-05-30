package sovsen.plejeren.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import sovsen.plejeren.R;

public class ClientListActivity extends AppCompatActivity {

    private Button Client_1_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list);


        Client_1_Button = (Button) findViewById(R.id.Client_1);

        Client_1_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent openWorkplan = new Intent(ClientListActivity.this, WorkplanActivity.class);
                startActivity(openWorkplan);

            }
        });
    }
}
