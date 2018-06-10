package sovsen.plejeren.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import sovsen.plejeren.R;
import sovsen.plejeren.view.presenter.TaskList;


public class WorkplanActivity extends AppCompatActivity {

    DatabaseReference databaseTasks;

    ListView listViewTask;

    ArrayList<sovsen.plejeren.view.presenter.Task> taskArrayList;

    TaskList adapter;

    Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workplan);

        databaseTasks = FirebaseDatabase.getInstance().getReference().child("Tasks");

        listViewTask = (ListView) findViewById(R.id.listViewTask);

        taskArrayList = new ArrayList<>();

        adapter = new TaskList(WorkplanActivity.this, taskArrayList);

        listViewTask.setAdapter(adapter);

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

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
