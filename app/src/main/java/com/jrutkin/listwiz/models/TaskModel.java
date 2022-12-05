package com.jrutkin.listwiz.models;

public class TaskModel {
    private String taskName;
    private String taskDesc;

    public TaskModel(String taskName, String taskDesc) {
        this.taskName = taskName;
        this.taskDesc = taskDesc;
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
}
