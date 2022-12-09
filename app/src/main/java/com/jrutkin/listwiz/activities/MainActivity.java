package com.jrutkin.listwiz.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.jrutkin.listwiz.R;
import com.jrutkin.listwiz.adapter.TaskRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    public static final String TASK_DESC_TAG = "taskDesc";
    public static final String TASK_NAME_TAG = "taskName";
    public static final String TASK_STATUS_TAG = "taskStatus";

    private List<Task> taskList;
    private TaskRecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskList = new ArrayList<>();
        buttonSetup();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Amplify.API.query(
                ModelQuery.list(Task.class),
                success -> {
                    Log.i(TAG, "MainActivity.OnResume(): Successfully got tasks");
                    for(Task task:success.getData()){
                        taskList.add(task);
                    }
                    runOnUiThread(() -> adapter.notifyDataSetChanged());
                },
                failure -> Log.i(TAG, "MainActivity.OnResume(): Failed to get tasks")
        );

        greetingSetup();
        recyclerViewSetup();
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

    private void recyclerViewSetup() {

        // dummy list of tasks
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
        adapter = new TaskRecyclerViewAdapter(taskList, this);
        taskRV.setAdapter(adapter);
    }

    public void goToTaskDetailViaButton(Button taskButton){
        Intent goToTaskDetail = new Intent(this, TaskDetailActivity.class);
        goToTaskDetail.putExtra(TASK_NAME_TAG, taskButton.getText().toString());
        startActivity(goToTaskDetail);
    }
}