package com.jrutkin.listwiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSetup();
    }

    private void buttonSetup(){
        Button addTaskButton = MainActivity.this.findViewById(R.id.MainButtonAddTask);
        addTaskButton.setOnClickListener(view -> {
            Intent goToAddTaskActivity = new Intent(this, AddTask.class);
            startActivity(goToAddTaskActivity);
        });

        Button allTasksButton = MainActivity.this.findViewById(R.id.MainButtonAllTasks);
        allTasksButton.setOnClickListener(view -> {
            Intent goToAllTasksActivity = new Intent(this, AllTasks.class);
            startActivity(goToAllTasksActivity);
        });
    }
}