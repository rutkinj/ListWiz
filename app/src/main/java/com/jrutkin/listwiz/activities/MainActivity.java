package com.jrutkin.listwiz.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.auth.cognito.result.AWSCognitoAuthSignOutResult;
import com.amplifyframework.auth.cognito.result.GlobalSignOutError;
import com.amplifyframework.auth.cognito.result.HostedUIError;
import com.amplifyframework.auth.cognito.result.RevokeTokenError;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.jrutkin.listwiz.R;
import com.jrutkin.listwiz.activities.auth.SignInActivity;
import com.jrutkin.listwiz.activities.auth.SignUpActivity;
import com.jrutkin.listwiz.adapter.TaskRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    public static final String TASK_DESC_TAG = "taskDesc";
    public static final String TASK_NAME_TAG = "taskName";
    public static final String TASK_STATUS_TAG = "taskStatus";
    public static final String TASK_IMAGE_KEY_TAG = "taskImageKey";

    SharedPreferences sharedPrefs;
    private List<Task> taskList;
    private TaskRecyclerViewAdapter adapter;
    public AuthUser authUser = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        hardcode s3/////////////
//
//        end hardcode/////////////
        buttonSetup();
        logoutButtonSetup();
    }

    @Override
    protected void onResume() {
        super.onResume();

        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        taskList = new ArrayList<>();

        Amplify.API.query(
                ModelQuery.list(Task.class),
                success -> {
                    Log.i(TAG, "MainActivity.OnResume(): Successfully got tasks");
                    for(Task task:success.getData()){

                        if(task.getTaskOwner().getName().equals(sharedPrefs.getString(UserProfileActivity.USER_TEAM_TAG,"No team"))) {
                            taskList.add(task);
                        }
                    }
                    runOnUiThread(() -> adapter.notifyDataSetChanged());
                },
                failure -> Log.i(TAG, "MainActivity.OnResume(): Failed to get tasks")
        );

        greetingSetup();
        recyclerViewSetup();

        Amplify.Auth.fetchUserAttributes(
                attributes -> {
                    Log.i(TAG, "Successfully got user attributes: " + attributes);
//                    authUser = attributes.
                },
                failure -> Log.w(TAG, "Failed to fetch user attributes")
        );

//        Button signIn = findViewById(R.id.MainButtonSignIn);
//        Button signUp = findViewById(R.id.MainButtonSignUp);
//        Button signOut = findViewById(R.id.MainButtonSignOut);


        // get current authenticted user
        // if user is null -> show signUp button, hide signIn button
//        if (authUser == null){
//            // not signed in: see sign up sign in hide logout
//            signIn.setVisibility(View.VISIBLE);
//            signUp.setVisibility(View.VISIBLE);
//            signOut.setVisibility(View.INVISIBLE);
//        } else {
//            String username = authUser.getUsername();
//            Log.i(TAG, "Username is: " + username);
//            // signed in. hhide sign up and sign in and show logout
//            signIn.setVisibility(View.INVISIBLE);
//            signUp.setVisibility(View.INVISIBLE);
//            signOut.setVisibility(View.VISIBLE);
//        }
    }

    private void buttonSetup(){
        Button addTaskButton = MainActivity.this.findViewById(R.id.MainButtonAddTask);
        addTaskButton.setOnClickListener(view -> {
            Intent goToAddTaskActivity = new Intent(this, AddTaskActivity.class);
            startActivity(goToAddTaskActivity);
        });

        Button allTasksButton = MainActivity.this.findViewById(R.id.MainButtonAllTasks);
        allTasksButton.setOnClickListener(view -> {
            Intent goToAllTasksActivity = new Intent(this, LocationActivity.class);
            startActivity(goToAllTasksActivity);
        });

        ImageView toUserProfileIV = MainActivity.this.findViewById(R.id.MainIVToUserProfile);
        toUserProfileIV.setOnClickListener(view -> {
            Intent goToUserProfileActivity = new Intent(this, UserProfileActivity.class);
            startActivity(goToUserProfileActivity);
        });

        findViewById(R.id.MainButtonSignIn).setOnClickListener(view -> {
            Intent goToSignInActivity = new Intent(this, SignInActivity.class);
            startActivity(goToSignInActivity);
        });

        findViewById(R.id.MainButtonSignUp).setOnClickListener(view -> {
            Intent goToSignUpActivity = new Intent(this, SignUpActivity.class);
            startActivity(goToSignUpActivity);
        });
    }

    public void greetingSetup(){
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

    public void logoutButtonSetup(){
        findViewById(R.id.MainButtonSignOut).setOnClickListener(view ->{
            Amplify.Auth.signOut( signOutResult -> {
                if (signOutResult instanceof AWSCognitoAuthSignOutResult.CompleteSignOut) {
                    // Sign Out completed fully and without errors.
                    Log.i("AuthQuickStart", "Signed out successfully");
                } else if (signOutResult instanceof AWSCognitoAuthSignOutResult.PartialSignOut) {
                    // Sign Out completed with some errors. User is signed out of the device.
                    AWSCognitoAuthSignOutResult.PartialSignOut partialSignOutResult =
                            (AWSCognitoAuthSignOutResult.PartialSignOut) signOutResult;

                    HostedUIError hostedUIError = partialSignOutResult.getHostedUIError();
                    if (hostedUIError != null) {
                        Log.e("AuthQuickStart", "HostedUI Error", hostedUIError.getException());
                        // Optional: Re-launch hostedUIError.getUrl() in a Custom tab to clear Cognito web session.
                    }

                    GlobalSignOutError globalSignOutError = partialSignOutResult.getGlobalSignOutError();
                    if (globalSignOutError != null) {
                        Log.e("AuthQuickStart", "GlobalSignOut Error", globalSignOutError.getException());
                        // Optional: Use escape hatch to retry revocation of globalSignOutError.getAccessToken().
                    }

                    RevokeTokenError revokeTokenError = partialSignOutResult.getRevokeTokenError();
                    if (revokeTokenError != null) {
                        Log.e("AuthQuickStart", "RevokeToken Error", revokeTokenError.getException());
                        // Optional: Use escape hatch to retry revocation of revokeTokenError.getRefreshToken().
                    }
                } else if (signOutResult instanceof AWSCognitoAuthSignOutResult.FailedSignOut) {
                    AWSCognitoAuthSignOutResult.FailedSignOut failedSignOutResult =
                            (AWSCognitoAuthSignOutResult.FailedSignOut) signOutResult;
                    // Sign Out failed with an exception, leaving the user signed in.
                    Log.e("AuthQuickStart", "Sign out Failed", failedSignOutResult.getException());
                }
            });
        });
    }
}