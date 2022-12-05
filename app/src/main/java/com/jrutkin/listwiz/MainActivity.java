package com.jrutkin.listwiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String TASK_NAME_TAG = "taskName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSetup();
        taskButtonsSetup();
    }

    @Override
    protected void onResume() {
        super.onResume();

        greetingSetup();
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

        ImageView toUserProfileIV = MainActivity.this.findViewById(R.id.MainIVToUserProfile);
        toUserProfileIV.setOnClickListener(view -> {
            Intent goToUserProfileActivity = new Intent(this, UserProfileActivity.class);
            startActivity(goToUserProfileActivity);
        });
    }

    public void greetingSetup(){
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String username = sharedPrefs.getString(UserProfileActivity.USERNAME_TAG, "No username");

        ((TextView)findViewById(R.id.MainTVUsername)).setText(username);
    }

    public void taskButtonsSetup(){

        Button gymButton = findViewById(R.id.MainButtonTaskGym);
        gymButton.setOnClickListener(view -> {
            goToTaskDetailViaButton(gymButton);
        });

        Button tanButton = findViewById(R.id.MainButtonTaskTan);
        tanButton.setOnClickListener(view -> {
            goToTaskDetailViaButton(tanButton);
        });
        Button laundryButton = findViewById(R.id.MainButtonTaskLaundry);
        laundryButton.setOnClickListener(view -> {
            goToTaskDetailViaButton(laundryButton);
        });
    }

    public void goToTaskDetailViaButton(Button taskButton){
        Intent goToTaskDetail = new Intent(this, TaskDetailActivity.class);
        goToTaskDetail.putExtra(TASK_NAME_TAG, taskButton.getText().toString());
        startActivity(goToTaskDetail);
    }
}