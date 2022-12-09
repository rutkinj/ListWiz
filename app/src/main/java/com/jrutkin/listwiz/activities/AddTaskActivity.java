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
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.StatusEnum;
import com.amplifyframework.datastore.generated.model.Task;
import com.jrutkin.listwiz.R;

public class AddTaskActivity extends AppCompatActivity {
    public static final String TAG = "AddTaskActivity";

    Spinner statusSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        // SETUP
        statusSpinnerSetup();
        buttonSetup();
    }

    private void buttonSetup() {
        Button addTaskButton = AddTaskActivity.this.findViewById(R.id.AddButton);
        addTaskButton.setOnClickListener(view -> {
            // GET INFO
            Task newTask = Task.builder()
                    .name(((EditText)findViewById(R.id.AddETTaskTitle)).getText().toString())
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
        statusSpinner = findViewById(R.id.AddTaskSpinner);
        statusSpinner.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                StatusEnum.values()
        ));
    }
}