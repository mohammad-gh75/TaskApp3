package org.maktab36.taskapp.model;

import android.webkit.URLUtil;

import java.io.Serializable;
import java.util.UUID;

public class User {
    private UUID mId;
    private String mUsername;
    private String mPassword;

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public User() {
        mId=UUID.randomUUID();
    }

    public User(String username, String password) {
        this();
        mUsername = username;
        mPassword = password;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return mUsername.equals(user.mUsername) &&
                mPassword.equals(user.mPassword);
    }
}
