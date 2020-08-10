package org.maktab36.taskapp.controller.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.maktab36.taskapp.R;
import org.maktab36.taskapp.model.Task;
import org.maktab36.taskapp.model.TaskState;
import org.maktab36.taskapp.repository.TaskRepository;
import org.maktab36.taskapp.util.TaskListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class TaskListFragment extends Fragment {
    public static final int TASK_DETAIL_REQUEST_CODE = 0;
    public static final String DIALOG_FRAGMENT_TAG = "Dialog";
    public static final String ARG_TASK_STATE = "taskState";
    private RecyclerView mRecyclerView;
    private TaskListAdapter mAdapter;
    private List<Task> mTasks;
    private ConstraintLayout mEmptyListLayout;

    public TaskListFragment() {
        // Required empty public constructor
    }

    public TaskListAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(TaskListAdapter adapter) {
        mAdapter = adapter;
    }

    public static TaskListFragment newInstance(TaskState state) {
        TaskListFragment fragment = new TaskListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TASK_STATE, state.toString());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTasks = getTaskList();
    }

    private List<Task> getTaskList() {
        TaskState state = TaskState.valueOf(getArguments().getString(ARG_TASK_STATE));
        switch (state) {
            case DOING:
                return TaskRepository.getInstance().getDoingTasks();
            case DONE:
                return TaskRepository.getInstance().getDoneTasks();
            case TODO:
                return TaskRepository.getInstance().getToDoTasks();
        }
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        findViews(view);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        updateUI();
//        setListener();
        changeLayout();
        return view;
    }

    private void changeLayout() {
        if(mTasks.size()==0){
            mRecyclerView.setVisibility(View.GONE);
            mEmptyListLayout.setVisibility(View.VISIBLE);
        }else{
            mRecyclerView.setVisibility(View.VISIBLE);
            mEmptyListLayout.setVisibility(View.GONE);
        }
    }

    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view_tasks);
        mEmptyListLayout=view.findViewById(R.id.empty_list_layout);
    }

    /*private void setListener(){
    }*/

    private void updateUI() {
        mAdapter = new TaskListAdapter(this, mTasks);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode!= Activity.RESULT_OK||data==null){
            return;
        }
        if(requestCode==TASK_DETAIL_REQUEST_CODE){

        }
    }
}