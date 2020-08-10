package org.maktab36.taskapp.controller.activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import org.maktab36.taskapp.controller.fragment.TaskBuilderFragment;

public class TaskBuilderActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, TaskBuilderActivity.class);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        return TaskBuilderFragment.newInstance();
    }
}