package org.maktab36.taskapp.repository;

import org.maktab36.taskapp.model.Task;
import org.maktab36.taskapp.model.TaskState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class TaskRepository {
    private static TaskRepository sTaskRepository;
    private String mTaskName="task";
    private int mTaskNumber=2;
    private List<Task> mToDoTasks;
    private List<Task> mDoneTasks;
    private List<Task> mDoingTasks;

    public static TaskRepository getInstance() {
        if(sTaskRepository==null){
            sTaskRepository=new TaskRepository();
        }
        return sTaskRepository;
    }

    private TaskRepository(){
        mToDoTasks=new ArrayList<>();
        mDoneTasks=new ArrayList<>();
        mDoingTasks=new ArrayList<>();
        for (int i = 1; i <= mTaskNumber; i++) {
            Random random = new Random();
            TaskState[] taskStates = TaskState.values();
            int r = random.nextInt(taskStates.length);
            Task task = new Task(mTaskName + "#" + i, taskStates[r]);
//            mTasks.add(task);
            switch (task.getState()) {
                case TODO:
                    mToDoTasks.add(task);
                    break;
                case DONE:
                    mDoneTasks.add(task);
                    break;
                case DOING:
                    mDoingTasks.add(task);
                    break;
            }
        }
    }



    public String getTaskName() {
        return mTaskName;
    }

    public void setTaskName(String taskName) {
        mTaskName = taskName;
    }

    public int getTaskNumber() {
        return mTaskNumber;
    }

    public void setTaskNumber(int taskNumber) {
        mTaskNumber = taskNumber;
    }

    public List<Task> getToDoTasks() {
        return mToDoTasks;
    }

    public void setToDoTasks(List<Task> toDoTasks) {
        mToDoTasks = toDoTasks;
    }

    public List<Task> getDoneTasks() {
        return mDoneTasks;
    }

    public void setDoneTasks(List<Task> doneTasks) {
        mDoneTasks = doneTasks;
    }

    public List<Task> getDoingTasks() {
        return mDoingTasks;
    }

    public void setDoingTasks(List<Task> doingTasks) {
        mDoingTasks = doingTasks;
    }


    public Task get(UUID uuid) {
        for (Task task:mToDoTasks) {
            if(task.getId().equals(uuid)){
                return task;
            }
        }
        for (Task task:mDoingTasks) {
            if(task.getId().equals(uuid)){
                return task;
            }
        }
        for (Task task:mDoneTasks) {
            if(task.getId().equals(uuid)){
                return task;
            }
        }
        return null;
    }

    public void addRandomTask(){
        Random random = new Random();
        TaskState[] taskStates = TaskState.values();
        int r = random.nextInt(taskStates.length);
        Task task = new Task(mTaskName + "#" + (mTaskNumber+1), taskStates[r]);
        insert(task);
    }




    public void update(Task task) {
        Task updateTask=get(task.getId());
        if(updateTask!=null) {
            if (updateTask.getState() == task.getState()) {
                updateTask.setName(task.getName());
                updateTask.setDescription(task.getDescription());
                updateTask.setDate(task.getDate());
            } else {
                switch (updateTask.getState()) {
                    case DOING:
                        mDoingTasks.remove(updateTask);
                        break;
                    case DONE:
                        mDoneTasks.remove(updateTask);
                        break;
                    case TODO:
                        mToDoTasks.remove(updateTask);
                        break;
                }
                switch (task.getState()) {
                    case TODO:
                        mToDoTasks.add(task);
                        break;
                    case DONE:
                        mDoneTasks.add(task);
                        break;
                    case DOING:
                        mDoingTasks.add(task);
                        break;
                }
            }
        }
    }

    public void delete(Task task) {
        for (int i = 0; i <mDoingTasks.size() ; i++) {
            if(mDoingTasks.get(i).getId().equals(task.getId())){
                mDoingTasks.remove(i);
                mTaskNumber--;
                return;
            }
        }
        for (int i = 0; i <mDoneTasks.size() ; i++) {
            if(mDoneTasks.get(i).getId().equals(task.getId())){
                mDoneTasks.remove(i);
                mTaskNumber--;
                return;
            }
        }
        for (int i = 0; i <mToDoTasks.size() ; i++) {
            if(mToDoTasks.get(i).getId().equals(task.getId())){
                mToDoTasks.remove(i);
                mTaskNumber--;
                return;
            }
        }
    }


    public void insert(Task task) {
        mTaskNumber++;
//        mTasks.add(task);
        switch (task.getState()){
            case DOING:
                mDoingTasks.add(task);
                break;
            case DONE:
                mDoneTasks.add(task);
                break;
            case TODO:
                mToDoTasks.add(task);
                break;
        }
    }

    public void deleteAll(){
        for (int i = 0; i <mDoingTasks.size() ; i++) {
            mDoingTasks.remove(0);
        }
        for (int i = 0; i <mDoneTasks.size() ; i++) {
            mDoneTasks.remove(0);
        }
        for (int i = 0; i <mToDoTasks.size() ; i++) {
            mToDoTasks.remove(0);
        }
    }


    /*public void insertList(List<Task> list) {
    }

    public int getPosition(UUID uuid) {
        return 0;
    }*/
}
