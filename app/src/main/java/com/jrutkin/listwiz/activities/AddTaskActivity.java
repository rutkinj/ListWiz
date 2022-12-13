package com.jrutkin.listwiz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.StatusEnum;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.TaskOwner;
import com.jrutkin.listwiz.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AddTaskActivity extends AppCompatActivity {

    public static final String TAG = "AddTaskActivity";

    Spinner statusSpinner;
    Spinner teamSpinner;
    CompletableFuture<List<TaskOwner>> taskOwnersFuture = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        // completable future
        taskOwnersFuture = new CompletableFuture<>();
        Amplify.API.query(
                ModelQuery.list(TaskOwner.class),
                success -> {
                    Log.i(TAG,"Successfully read from owners");
                    ArrayList<String> ownerNames = new ArrayList<>();
                    ArrayList<TaskOwner> taskOwners = new ArrayList<>();
                    for (TaskOwner taskOwner: success.getData()){
                        ownerNames.add(taskOwner.getName());
                        taskOwners.add(taskOwner);
                    }
                    taskOwnersFuture.complete(taskOwners);
                    runOnUiThread(() -> {
                        teamSpinnerSetup(ownerNames);
                    });
                },
                failure -> {
                    taskOwnersFuture.complete(null);
                    Log.w(TAG,"Failed to read from owners");
                });

        // SETUP
        statusSpinnerSetup();
        buttonSetup();
//        dummyOwnersSetup();
    }

    private void buttonSetup() {
        Button addTaskButton = AddTaskActivity.this.findViewById(R.id.AddButton);
        addTaskButton.setOnClickListener(view -> {
            // MATCH STRING OWNER
            String taskOwnerString = teamSpinner.getSelectedItem().toString();
            List<TaskOwner> allTaskOwners = null;
            try{
                allTaskOwners = taskOwnersFuture.get();
            }
            catch (InterruptedException ie){
                Log.e(TAG, "InterruptedException while getting owners");
                Thread.currentThread().interrupt();
            }
            catch (ExecutionException ee){
                Log.w(TAG, "ExecutionException while getting owners.");
            }
            TaskOwner selectedOwner = allTaskOwners.stream().filter(owner -> owner.getName().equals(taskOwnerString))
                    .findAny().orElseThrow(RuntimeException::new);
            // GET INFO
            Task newTask = Task.builder()
                    .name(((EditText)findViewById(R.id.AddETTaskTitle)).getText().toString())
                    .taskOwner(selectedOwner)
                    .description(((EditText)findViewById(R.id.AddETTaskDesc)).getText().toString())
                    .status((StatusEnum) statusSpinner.getSelectedItem())
                    .build();
            // ADD TO DB
            Amplify.API.mutate(
                    ModelMutation.create(newTask),
                    success -> Log.i(TAG, "AddTaskActivity.onCreate(): successfully created task."),
                    failure -> Log.w(TAG, "AddTaskActivity.onCreate(): failed to create task.", failure)
            );
            // TOAST
            Toast.makeText(this, "Task Submitted", Toast.LENGTH_SHORT).show();
        });
    }

    public void statusSpinnerSetup() {
        // must add spinner first
        statusSpinner = findViewById(R.id.AddTaskSpinnerStatus);
        statusSpinner.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                StatusEnum.values()
        ));
    }

    public void teamSpinnerSetup(ArrayList<String> ownerNames){
        teamSpinner = findViewById(R.id.AddTaskSpinnerTeam);
        teamSpinner.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                ownerNames
        ));
    }

    // hardcoded teams, add to aws db
    public void dummyOwnersSetup(){
        TaskOwner dummy1 = TaskOwner.builder()
                .name("Bob")
                .email("bob@bob.bob")
                .build();

        TaskOwner dummy2 = TaskOwner.builder()
                .name("Zob")
                .email("zob@zob.zob")
                .build();

        TaskOwner dummy3 = TaskOwner.builder()
                .name("Grob")
                .email("grob@grob.grob")
                .build();

        Amplify.API.mutate(
                ModelMutation.create(dummy1),
                success -> {},
                failure -> {}
        );

        Amplify.API.mutate(
                ModelMutation.create(dummy2),
                success -> {},
                failure -> {}
        );

        Amplify.API.mutate(
                ModelMutation.create(dummy3),
                success -> {},
                failure -> {}
        );
    }
}