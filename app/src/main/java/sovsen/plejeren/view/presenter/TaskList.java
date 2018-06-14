package sovsen.plejeren.view.presenter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import sovsen.plejeren.R;

public class TaskList extends ArrayAdapter<Task> {

    private Activity context;
    private List<Task> taskList;

    public TaskList(@NonNull Activity context, List<Task> taskList) {
        super(context, 0, taskList);
        this.context = context;
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.task_listlayout, null, true);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);

        Task task = taskList.get(position);

        textViewName.setText(task.getTaskName());

        return listViewItem;
    }



}
