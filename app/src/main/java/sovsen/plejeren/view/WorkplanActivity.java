package sovsen.plejeren.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import sovsen.plejeren.R;
import sovsen.plejeren.view.presenter.TaskList;


public class WorkplanActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseReference databaseTasks;

    ListView listViewTask;

    ArrayList<sovsen.plejeren.view.presenter.Task> taskArrayList;

    TaskList taskAdapter;

    Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workplan);

        databaseTasks = FirebaseDatabase.getInstance().getReference().child("Tasks");

        listViewTask = (ListView) findViewById(R.id.listViewTask);

        taskArrayList = new ArrayList<>();

        taskAdapter = new TaskList(WorkplanActivity.this, taskArrayList);

        listViewTask.setAdapter(taskAdapter);

        Spinner spinner1 = findViewById(R.id.spinner1);
        Spinner spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tasks_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);


    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseTasks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot taskSnapshot : dataSnapshot.getChildren()) {
                    DataSnapshot childsnapshot = taskSnapshot.child("Name");
                    String taskName = (String) childsnapshot.getValue();

                    taskArrayList.add(new sovsen.plejeren.view.presenter.Task(taskName));
                }

                taskAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
