package com.jrutkin.listwiz.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.jrutkin.listwiz.dao.TaskDao;
import com.jrutkin.listwiz.models.TaskModel;

@TypeConverters({WizDatabaseConverters.class})

@Database(entities = {TaskModel.class}, version = 1 /*!!updating version drops tables!!*/)
public abstract class WizDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
