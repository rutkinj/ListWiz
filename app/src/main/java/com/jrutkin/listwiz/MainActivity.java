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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSetup();
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
}