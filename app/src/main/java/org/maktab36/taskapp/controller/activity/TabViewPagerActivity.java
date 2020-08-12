package org.maktab36.taskapp.controller.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.maktab36.taskapp.R;
import org.maktab36.taskapp.controller.fragment.TaskDetailFragment;
import org.maktab36.taskapp.controller.fragment.TaskListFragment;
import org.maktab36.taskapp.model.Task;
import org.maktab36.taskapp.model.TaskState;
import org.maktab36.taskapp.repository.TaskRepository;

import java.util.Random;
import java.util.UUID;

public class TabViewPagerActivity extends AppCompatActivity {
    public static final String DIALOG_FRAGMENT_TAG = "activityDialog";
    private FloatingActionButton mActionButton;
    private TabLayout mTabLayout;
    private ViewPager2 mTabViewPager;
    private FragmentStateAdapter mViewPagerAdapter;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, TabViewPagerActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_view_pager);

        findViews();
        updateUI();
        setListener();

        new TabLayoutMediator(mTabLayout, mTabViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 1:
                        tab.setText(TaskState.DOING.toString());
                        break;
                    case 2:
                        tab.setText(TaskState.DONE.toString());
                        break;
                    default:
                        tab.setText(TaskState.TODO.toString());
                        break;
                }
            }
        }).attach();
    }

    private void findViews() {
        mActionButton = findViewById(R.id.floating_action_button_add_task);
        mTabLayout = findViewById(R.id.tab_layout);
        mTabViewPager = findViewById(R.id.view_pager_tabs);
    }

    private void setListener() {
        mActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                TaskRepository.getInstance().addRandomTask();
                TaskDetailFragment taskDetailFragment = TaskDetailFragment.newInstance(null);
                taskDetailFragment.show(getSupportFragmentManager(),
                        DIALOG_FRAGMENT_TAG);
            }
        });
    }

    public void updateFragments() {
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            if (fragment instanceof TaskListFragment) {
                TaskListFragment listFragment = (TaskListFragment) fragment;
                listFragment.updateUI();
            }
        }
    }


    private void updateUI() {
        mViewPagerAdapter = new TaskViewPagerAdapter(this);
        mTabViewPager.setAdapter(mViewPagerAdapter);

    }

    private class TaskViewPagerAdapter extends FragmentStateAdapter {
        public TaskViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 1:
                    return TaskListFragment.newInstance(TaskState.DOING);
                case 2:
                    return TaskListFragment.newInstance(TaskState.DONE);
                default:
                    return TaskListFragment.newInstance(TaskState.TODO);
            }
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }
}