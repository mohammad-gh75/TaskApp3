package org.maktab36.taskapp.repository;

import org.maktab36.taskapp.model.Task;
import org.maktab36.taskapp.model.TaskState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class TaskRepository {
    private static TaskRepository sTaskRepository;
    private List<Task> mToDoTasks;
    private List<Task> mDoneTasks;
    private List<Task> mDoingTasks;
    private UUID mAdminId;

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
        mAdminId=UserRepository.getInstance().getAdmin().getId();
    }

    public List<Task> getToDoTasks(UUID userId) {
        if(userId.equals(mAdminId)){
            return mToDoTasks;
        }else {
            List<Task> userToDoTask = new ArrayList<>();
            for (int i = 0; i < mToDoTasks.size(); i++) {
                if (mToDoTasks.get(i).getUserId().equals(userId)) {
                    userToDoTask.add(mToDoTasks.get(i));
                }
            }
            return userToDoTask;
        }
    }


    public List<Task> getDoneTasks(UUID userId) {
        if(userId.equals(mAdminId)){
            return mDoneTasks;
        }else {
            List<Task> userDoneTask = new ArrayList<>();
            for (int i = 0; i < mDoneTasks.size(); i++) {
                if (mDoneTasks.get(i).getUserId().equals(userId)) {
                    userDoneTask.add(mDoneTasks.get(i));
                }
            }
            return userDoneTask;
        }
    }


    public List<Task> getDoingTasks(UUID userId) {
        if(userId.equals(mAdminId)){
            return mDoingTasks;
        }else {
            List<Task> userDoingTask = new ArrayList<>();
            for (int i = 0; i < mDoingTasks.size(); i++) {
                if (mDoingTasks.get(i).getUserId().equals(userId)) {
                    userDoingTask.add(mDoingTasks.get(i));
                }
            }
            return userDoingTask;
        }
    }

    public Task get(UUID userId,UUID uuid) {
        for (Task task:mToDoTasks) {
            if((task.getUserId().equals(userId)||userId.equals(mAdminId))&&task.getId().equals(uuid)){
                return task;
            }
        }
        for (Task task:mDoingTasks) {
            if((task.getUserId().equals(userId)||userId.equals(mAdminId))&&task.getId().equals(uuid)){
                return task;
            }
        }
        for (Task task:mDoneTasks) {
            if((task.getUserId().equals(userId)||userId.equals(mAdminId))&&task.getId().equals(uuid)){
                return task;
            }
        }
        return null;
    }

    public void update(UUID userId,Task task) {
        Task updateTask=get(userId,task.getId());
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
                return;
            }
        }
        for (int i = 0; i <mDoneTasks.size() ; i++) {
            if(mDoneTasks.get(i).getId().equals(task.getId())){
                mDoneTasks.remove(i);
                return;
            }
        }
        for (int i = 0; i <mToDoTasks.size() ; i++) {
            if(mToDoTasks.get(i).getId().equals(task.getId())){
                mToDoTasks.remove(i);
                return;
            }
        }
    }


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

    public void deleteAll(UUID userId){
        for (int i = 0; i <mDoingTasks.size() ; i++) {
            if (userId.equals(mAdminId)||mDoingTasks.get(i).getUserId().equals(userId)) {
                mDoingTasks.remove(i);
                i--;
            }
        }
        for (int i = 0; i <mDoneTasks.size() ; i++) {
            if (userId.equals(mAdminId)||mDoneTasks.get(i).getUserId().equals(userId)) {
                mDoneTasks.remove(i);
                i--;
            }
        }
        for (int i = 0; i <mToDoTasks.size() ; i++) {
            if (userId.equals(mAdminId)||mToDoTasks.get(i).getUserId().equals(userId)) {
                mToDoTasks.remove(i);
                i--;
            }
        }
    }

}
