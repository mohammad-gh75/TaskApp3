package org.maktab36.taskapp.controller.activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import org.maktab36.taskapp.controller.fragment.TaskTabFragment;

public class TaskListActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, TaskListActivity.class);

        return intent;
    }


    @Override
    public Fragment createFragment() {
        return TaskTabFragment.newInstance();
    }
}