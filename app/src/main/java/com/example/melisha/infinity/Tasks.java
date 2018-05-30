package com.example.melisha.infinity;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Tasks {
    public String taskID;
    public String taskName;
    public String taskDesc;
    public String taskDueOn;
    public String taskAssignee;
    public String taskStatus;

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String getTaskDueOn() {
        return taskDueOn;
    }

    public void setTaskDueOn(String taskDueOn) {
        this.taskDueOn = taskDueOn;
    }

    public String getTaskAssignee() {
        return taskAssignee;
    }

    public void setTaskAssignee(String taskAssignee) {
        this.taskAssignee = taskAssignee;
    }

    public Tasks() {
    }

    public Tasks(String taskID,String taskName, String taskDesc, String taskDueOn,String taskAssignee, String taskStatus) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.taskDesc = taskDesc;
        this.taskDueOn = taskDueOn;
        this.taskAssignee = taskAssignee;
        this.taskStatus = taskStatus;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("taskID", taskID);
        result.put("taskName", taskName);
        result.put("taskDesc", taskDesc);
        result.put("taskDueOn", taskDueOn);
        result.put("taskAssignee", taskAssignee);
        result.put("taskStatus", taskStatus);
        return result;
    }
}