package org.maktab36.taskapp.repository;

import org.maktab36.taskapp.model.Task;
import org.maktab36.taskapp.model.TaskState;

import java.util.List;
import java.util.UUID;

public class TaskRepository {
    private static TaskRepository sTaskRepository;
    private String mTaskName;
    private int mTaskNumber=-1;
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




   /* public void update(Task task) {
    }

    public void delete(Task task) {
    }*/


    public void insert(Task task) {
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


    /*public void insertList(List<Task> list) {
    }

    public int getPosition(UUID uuid) {
        return 0;
    }*/
}
