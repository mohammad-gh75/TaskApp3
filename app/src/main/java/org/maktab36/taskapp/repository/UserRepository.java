package org.maktab36.taskapp.repository;

import org.maktab36.taskapp.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserRepository {
    private static UserRepository sUserRepository;
    private static List<User> sUsers = new ArrayList<>();
    private User mCurrentUser;
    private User mAdmin;

    public static UserRepository getInstance() {
        if(sUserRepository==null){
            sUserRepository=new UserRepository();
        }
        return sUserRepository;
    }

    private UserRepository() {
        mAdmin=new User("admin","1234");
        sUsers.add(mAdmin);
    }

    public User getAdmin() {
        return mAdmin;
    }

    public User getCurrentUser() {
        return mCurrentUser;
    }

    public void setCurrentUser(User currentUser) {
        mCurrentUser = currentUser;
    }


    public void addUser(User user) {
        if (!containUser(user)) {
            sUsers.add(user);
        }
    }

    public User getUser(String username,String password){
        for (int i = 0; i <sUsers.size() ; i++) {
            if(sUsers.get(i).getUsername().equals(username)&&
                    sUsers.get(i).getPassword().equals(password)){
                return sUsers.get(i);
            }
        }
        return null;
    }

    public User getUser(UUID userId){
        for (int i = 0; i <sUsers.size() ; i++) {
            if(sUsers.get(i).getId().equals(userId)){
                return sUsers.get(i);
            }
        }
        return null;
    }

    public boolean containUser(User user) {
        return sUsers.contains(user);
    }

    public boolean containUser(String username){
        for (int i = 0; i <sUsers.size() ; i++) {
            if(sUsers.get(i).getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }
}
