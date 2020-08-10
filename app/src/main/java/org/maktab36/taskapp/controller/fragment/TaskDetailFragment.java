package org.maktab36.taskapp.controller.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import org.maktab36.taskapp.R;
import org.maktab36.taskapp.model.Task;
import org.maktab36.taskapp.repository.TaskRepository;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.UUID;

public class TaskDetailFragment extends DialogFragment {
    public static final String ARG_TASK_ID = "TaskId";
    private Task mCurrentTask;

    private EditText mEditTextTitle;
    private EditText mEditTextDescription;
    private Button mButtonDate;
    private Button mButtonTime;
    private RadioGroup mRadioGroupStates;
    private Button mButtonDelete;
    private Button mButtonEdit;
    private Button mButtonSave;
    private SimpleDateFormat mDateFormatter;
    private SimpleDateFormat mTimeFormatter;

    public TaskDetailFragment() {
        // Required empty public constructor
    }

    public static TaskDetailFragment newInstance(UUID taskId) {
        TaskDetailFragment fragment = new TaskDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_TASK_ID,taskId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID taskId= (UUID) getArguments().getSerializable(ARG_TASK_ID);
        mCurrentTask= TaskRepository.getInstance().get(taskId);

        mDateFormatter=new SimpleDateFormat("yyyy/MM/dd", Locale.US);
        mTimeFormatter=new SimpleDateFormat("HH:mm", Locale.US);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_task_detail, container, false);
        findViews(view);
        setUI();
        setListeners();
        return view;
    }

    private void findViews(View view) {
        mEditTextTitle=view.findViewById(R.id.edit_text_detail_title);
        mEditTextDescription=view.findViewById(R.id.edit_text_detail_description);
        mButtonDate=view.findViewById(R.id.button_detail_date);
        mButtonTime=view.findViewById(R.id.button_detail_time);
        mRadioGroupStates=view.findViewById(R.id.radio_group_states);
        mButtonDelete=view.findViewById(R.id.button_delete);
        mButtonEdit=view.findViewById(R.id.button_edit);
        mButtonSave=view.findViewById(R.id.button_save);
    }

    private void setUI() {
        mEditTextTitle.setText(mCurrentTask.getName());
        mEditTextDescription.setText(mCurrentTask.getDescription());
        mButtonDate.setText(mDateFormatter.format(mCurrentTask.getDate()));
        mButtonTime.setText(mTimeFormatter.format(mCurrentTask.getDate()));
        switch (mCurrentTask.getState()){
            case DOING:
                mRadioGroupStates.check(R.id.radio_button_state_doing);
                break;
            case DONE:
                mRadioGroupStates.check(R.id.radio_button_state_done);
                break;
            case TODO:
                mRadioGroupStates.check(R.id.radio_button_state_todo);
                break;
        }
    }
    
    private void setListeners() {

    }

    private void setResult(/*Date userSelectedDate*/) {
        Fragment fragment = getTargetFragment();
        Intent intent = new Intent();
        /*intent.putExtra(EXTRA_USER_SELECTED_DATE, userSelectedDate);*/
        fragment.onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
    }
}