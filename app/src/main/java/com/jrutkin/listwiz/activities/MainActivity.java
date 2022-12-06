package com.jrutkin.listwiz.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jrutkin.listwiz.R;
import com.jrutkin.listwiz.adapter.TaskRecyclerViewAdapter;
import com.jrutkin.listwiz.models.TaskModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    public static final String TASK_DESC_TAG = "taskDesc";

    public static final String TASK_NAME_TAG = "taskName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSetup();
        recyclerViewSetup();
    }

    private void recyclerViewSetup() {

        // dummy list of tasks
        List<TaskModel> taskList = new ArrayList<>();
//        taskList.add(new TaskModel("Gym","As in -> Hit that"));
//        taskList.add(new TaskModel("Tan","As in -> Get that"));
//        taskList.add(new TaskModel("Laundry","I wash that"));
//        taskList.add(new TaskModel("Sharpen Pencils","Gettin' dull"));
//        taskList.add(new TaskModel("Take a rest","Gettin' tired"));
//        taskList.add(new TaskModel("Blink eyes","Gotta get my 10,000 daily blinks"));
//        taskList.add(new TaskModel("Be Normal","The humans must not know"));
//        taskList.add(new TaskModel("Stretch","Taffy"));

        // setup recycler view
        RecyclerView taskRV = findViewById(R.id.MainRV);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        taskRV.setLayoutManager(layoutManager);
        TaskRecyclerViewAdapter adapter = new TaskRecyclerViewAdapter(taskList, this);
        taskRV.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();

        greetingSetup();
    }

    private void buttonSetup(){
        Button addTaskButton = MainActivity.this.findViewById(R.id.MainButtonAddTask);
        addTaskButton.setOnClickListener(view -> {
            Intent goToAddTaskActivity = new Intent(this, AddTaskActivity.class);
            startActivity(goToAddTaskActivity);
        });

        Button allTasksButton = MainActivity.this.findViewById(R.id.MainButtonAllTasks);
        allTasksButton.setOnClickListener(view -> {
            Intent goToAllTasksActivity = new Intent(this, AllTasksActivity.class);
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

    public void goToTaskDetailViaButton(Button taskButton){
        Intent goToTaskDetail = new Intent(this, TaskDetailActivity.class);
        goToTaskDetail.putExtra(TASK_NAME_TAG, taskButton.getText().toString());
        startActivity(goToTaskDetail);
    }
}