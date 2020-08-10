package org.maktab36.taskapp.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import org.maktab36.taskapp.R;
import org.maktab36.taskapp.controller.activity.TaskListActivity;
import org.maktab36.taskapp.model.Task;
import org.maktab36.taskapp.model.TaskState;
import org.maktab36.taskapp.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class TaskBuilderFragment extends Fragment {
    private Button mButtonBuild;
    private EditText mTextViewName;
    private EditText mTextViewNumber;

    public TaskBuilderFragment() {
        // Required empty public constructor
    }

    public static TaskBuilderFragment newInstance() {
        TaskBuilderFragment fragment = new TaskBuilderFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_builder, container, false);
        findViews(view);
        loadTexts();
        setListeners();
        return view;
    }

    private void loadTexts() {
        String taskName=TaskRepository.getInstance().getTaskName();
        int taskNumber=TaskRepository.getInstance().getTaskNumber();
        mTextViewName.setText(taskName);
        if(taskNumber!=-1) {
            mTextViewNumber.setText(String.valueOf(taskNumber));
        }
    }

    private void findViews(View view) {
        mButtonBuild = view.findViewById(R.id.button_build);
        mTextViewName = view.findViewById(R.id.edit_text_username);
        mTextViewNumber = view.findViewById(R.id.edit_text_number_signed);
    }

    private void setListeners() {
        mTextViewName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                TaskRepository.getInstance().setTaskName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        mTextViewNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().equals("")) {
                    int number = Integer.parseInt(charSequence.toString());
                    TaskRepository.getInstance().setTaskNumber(number);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        mButtonBuild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTaskLists();
                Intent intent = TaskListActivity.newIntent(getActivity());
                startActivity(intent);
            }
        });
    }

    private void setTaskLists() {
        String taskName = TaskRepository.getInstance().getTaskName();
        int taskNumber = TaskRepository.getInstance().getTaskNumber();
        List<Task> toDoTasks = new ArrayList<>();
        List<Task> doingTasks = new ArrayList<>();
        List<Task> doneTasks = new ArrayList<>();
        for (int i = 1; i <= taskNumber; i++) {
            Random random = new Random();
            TaskState[] taskStates = TaskState.values();
            int r = random.nextInt(taskStates.length);
            Task task = new Task(taskName + "#" + i, taskStates[r]);
            switch (task.getState()) {
                case TODO:
                    toDoTasks.add(task);
                    break;
                case DONE:
                    doneTasks.add(task);
                    break;
                case DOING:
                    doingTasks.add(task);
                    break;
            }
        }
        TaskRepository.getInstance().setDoingTasks(doingTasks);
        TaskRepository.getInstance().setDoneTasks(doneTasks);
        TaskRepository.getInstance().setToDoTasks(toDoTasks);
    }
}