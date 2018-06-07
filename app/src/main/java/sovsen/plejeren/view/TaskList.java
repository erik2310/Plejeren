package sovsen.plejeren.view;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import sovsen.plejeren.R;

public class TaskList extends ArrayAdapter<Task>{

    private Activity context;
    private List<Task> taskList;

    public TaskList(Activity context, List<Task> taskList){
        super(context, R.layout.task_listlayout);
        this.context = context;
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.task_listlayout, null, true);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);

        Task task = taskList.get(position);

        textViewName.setText(task.getTaskName());

        return listViewItem;
    }
}
