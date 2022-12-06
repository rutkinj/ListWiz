package com.jrutkin.listwiz.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.jrutkin.listwiz.models.TaskModel;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert
    public void insertTask(TaskModel task);

    @Query("SELECT * FROM TaskModel")
    public List<TaskModel> findAll();

    @Query("SELECT * FROM TaskModel WHERE id = :id")
    public TaskModel findById(long id);

    @Query("SELECT * FROM TaskModel WHERE taskStatus = :taskStatus")
    public List<TaskModel> findByStatus(TaskModel.TaskStatusEnum taskStatus);

    // make a delete and update
    @Delete
    public void delete(TaskModel taskModel);

    @Update
    public void update(TaskModel taskModel);
}
