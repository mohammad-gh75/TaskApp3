package org.maktab36.taskapp.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.maktab36.taskapp.R;
import org.maktab36.taskapp.controller.fragment.TaskDetailFragment;
import org.maktab36.taskapp.controller.fragment.TaskListFragment;
import org.maktab36.taskapp.model.Task;

import java.util.List;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskHolder> {
    private List<Task> mTaskList;
    private Fragment mFragment;

    public TaskListAdapter(Fragment fragment, List<Task> taskList) {
        mFragment=fragment;
        mTaskList = taskList;
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mFragment.getActivity());
        View view = inflater.inflate(R.layout.list_row_task, parent, false);
        return new TaskHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
        Task task = mTaskList.get(position);
        holder.bindTask(task);
    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
    }

    public class TaskHolder extends RecyclerView.ViewHolder{
        private Task mTask;
        private TextView mTextViewTaskName;
        private TextView mTextViewTaskState;

        public TaskHolder(@NonNull View itemView) {
            super(itemView);

            mTextViewTaskName = itemView.findViewById(R.id.text_view_task_name);
            mTextViewTaskState = itemView.findViewById(R.id.text_view_task_state);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TaskDetailFragment taskDetailFragment=TaskDetailFragment.newInstance(mTask.getId());
                    taskDetailFragment.setTargetFragment(mFragment,
                            TaskListFragment.TASK_DETAIL_REQUEST_CODE);
                    taskDetailFragment.show(mFragment.getFragmentManager(),
                            TaskListFragment.DIALOG_FRAGMENT_TAG);
                }
            });
        }

        public void bindTask(Task task) {
            mTask=task;
            mTextViewTaskName.setText(task.getName());
            mTextViewTaskState.setText(task.getState().toString());
        }
    }
}
