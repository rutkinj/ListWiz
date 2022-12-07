package com.jrutkin.listwiz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.jrutkin.listwiz.R;

public class TaskDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        taskDetailSetup();
    }

    public void taskDetailSetup(){
        // pass full task obj after aws integration
        Intent callingIntent = getIntent();
        String taskName = null;
        String taskDesc = null;
        String taskStatus = null;
        if (callingIntent != null){
            taskName = callingIntent.getStringExtra(MainActivity.TASK_NAME_TAG);
            taskDesc = callingIntent.getStringExtra(MainActivity.TASK_DESC_TAG);
            taskStatus = callingIntent.getStringExtra(MainActivity.TASK_STATUS_TAG);
        }
        TextView taskNameTV = findViewById(R.id.DetailTVTaskName);
        TextView taskDescTV = findViewById(R.id.DetailTVTaskDesc);
        TextView taskStatusTV = findViewById(R.id.DetailTVTaskStatus);
        if (taskName != null){
            taskNameTV.setText(taskName);
            taskDescTV.setText(taskDesc);
            taskStatusTV.setText(taskStatus);
        }
    }
}