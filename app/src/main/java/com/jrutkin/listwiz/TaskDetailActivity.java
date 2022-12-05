package com.jrutkin.listwiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TaskDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        getSetTaskName();
    }

    public void getSetTaskName(){
        Intent callingIntent = getIntent();
        String taskName = null;
        if (callingIntent != null){
            taskName = callingIntent.getStringExtra(MainActivity.TASK_NAME_TAG);
        }
        TextView taskNameTV = findViewById(R.id.DetailTVTaskName);
        if (taskName != null){
            taskNameTV.setText(taskName);
        }
    }
}