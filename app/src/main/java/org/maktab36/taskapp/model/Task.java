package org.maktab36.taskapp.model;

import org.maktab36.taskapp.util.DateUtils;

import java.util.Date;
import java.util.UUID;

public class Task {
    private UUID mId;
    private String mName;
    private TaskState mState;
    private String mDescription;
    private Date mDate;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public TaskState getState() {
        return mState;
    }

    public void setState(TaskState state) {
        mState = state;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public Task() {
        mId = UUID.randomUUID();
        mDate= DateUtils.getRandomDate(2000,2020);
    }

    public Task(String name, TaskState state) {
        this();
        mName = name;
        mState = state;
    }

}
