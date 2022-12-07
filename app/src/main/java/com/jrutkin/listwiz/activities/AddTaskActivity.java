package com.jrutkin.listwiz.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.jrutkin.listwiz.R;
import com.jrutkin.listwiz.database.WizDatabase;
import com.jrutkin.listwiz.models.TaskModel;

public class AddTaskActivity extends AppCompatActivity {

    WizDatabase wizDatabase;

    Spinner statusSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        // DB
        wizDatabase = Room.databaseBuilder(getApplicationContext(), WizDatabase.class, MainActivity.DB_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        // SETUP
        statusSpinnerSetup();
        buttonSetup();
    }

    private void buttonSetup() {
        Button addTaskButton = AddTaskActivity.this.findViewById(R.id.AddButton);
        addTaskButton.setOnClickListener(view -> {
            // GET INFO
            TaskModel newTask = new TaskModel(
                    ((EditText)findViewById(R.id.AddETTaskTitle)).getText().toString(),
                    ((EditText)findViewById(R.id.AddETTaskDesc)).getText().toString(),
                    TaskModel.TaskStatusEnum.fromString(statusSpinner.getSelectedItem().toString())
            );
            // ADD TO DB
            wizDatabase.taskDao().insertTask(newTask);
            // TOAST
            Toast.makeText(this, "Task Submitted", Toast.LENGTH_SHORT).show();
        });
    }

    public void statusSpinnerSetup() {
        // must add spinner first
        statusSpinner = findViewById(R.id.AddTaskSpinner);
        statusSpinner.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                TaskModel.TaskStatusEnum.values()
        ));
    }
}