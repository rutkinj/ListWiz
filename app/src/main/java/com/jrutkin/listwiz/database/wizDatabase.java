package com.jrutkin.listwiz.database;

import androidx.room.Database;
import androidx.room.TypeConverters;

import com.jrutkin.listwiz.dao.TaskDao;
import com.jrutkin.listwiz.models.TaskModel;

@TypeConverters({wizDatabase.class})

@Database(entities = {TaskModel.class}, version = 0 /*!!updating version drops tables!!*/)
public abstract class wizDatabase {
    public abstract TaskDao taskDao();
}
