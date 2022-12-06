package com.jrutkin.listwiz.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

import kotlinx.coroutines.scheduling.Task;

@Entity
public class TaskModel {
    @PrimaryKey(autoGenerate = true)
    private String taskName;
    private String taskDesc;
    private Date createdDate;

    private TaskStatusEnum taskStatus;

    public TaskModel(String taskName, String taskDesc, TaskStatusEnum taskStatus, Date createdDate) {
        this.taskName = taskName;
        this.taskDesc = taskDesc;
        this.taskStatus = taskStatus;
        this.createdDate = createdDate;
    }
    
    public TaskModel(){}

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

    // ENUM
    public enum TaskStatusEnum{
        FRESH("Fresh"),
        FORGOTTEN("Forgotten"),
        DOING("Doing"),
        COMPLETED("Completed");

        private final String taskStatus;

        TaskStatusEnum(String taskStatus) {
            this.taskStatus = taskStatus;
        }

        public String getTaskStatus(){
            return taskStatus;
        }

        public static TaskStatusEnum fromString(String status){
            for (TaskStatusEnum type: TaskStatusEnum.values()){
                if(type.taskStatus.equals(status)){
                    return type;
                }
            }
            return null;
        }

//        @Override
//        String toString(){
//            if (taskStatus == null){
//                return "";
//            }
//            return taskStatus;
//        }
    }
}
