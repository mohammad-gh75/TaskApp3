package org.maktab36.taskapp.repository;

import org.maktab36.taskapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static UserRepository sUserRepository;
    private static List<User> sUsers = new ArrayList<>();
    private User mCurrentUser;

    public User getCurrentUser() {
        return mCurrentUser;
    }

    public void setCurrentUser(User currentUser) {
        mCurrentUser = currentUser;
    }

    public static UserRepository getInstance() {
        if(sUserRepository==null){
            sUserRepository=new UserRepository();
        }
        return sUserRepository;
    }

    public UserRepository() {
    }

    public void addUser(User user) {
        if (!containUser(user)) {
            sUsers.add(user);
        }
    }

    public boolean containUser(User user) {
        return sUsers.contains(user);
    }
}
