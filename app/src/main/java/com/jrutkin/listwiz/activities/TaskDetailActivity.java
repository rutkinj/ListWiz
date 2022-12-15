package com.jrutkin.listwiz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.amplifyframework.core.Amplify;
import com.jrutkin.listwiz.R;

import java.io.File;

public class TaskDetailActivity extends AppCompatActivity {
    public static final String TAG = "TaskDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        taskDetailSetup();
    }

    public void taskDetailSetup(){
        // pass full task obj after aws integration

        Intent callingIntent = getIntent();
        String taskName = null;
        String taskDesc = null;
        String taskStatus = null;
        String taskImageTag;
        File taskImage;
        if (callingIntent != null){
            taskName = callingIntent.getStringExtra(MainActivity.TASK_NAME_TAG);
            taskDesc = callingIntent.getStringExtra(MainActivity.TASK_DESC_TAG);
            taskStatus = callingIntent.getStringExtra(MainActivity.TASK_STATUS_TAG);
            if (callingIntent.getStringExtra(MainActivity.TASK_IMAGE_KEY_TAG) != null){
                ImageView taskIV = findViewById(R.id.TaskDetailIV);

                taskImageTag = callingIntent.getStringExtra(MainActivity.TASK_IMAGE_KEY_TAG).split("/")[1];
                taskImage = new File(getApplicationContext().getFilesDir() +"/"+ taskImageTag);
                Amplify.Storage.downloadFile(
                        taskImageTag,
                        taskImage,
                        success -> {
                            Log.i(TAG, "Successfully downloaded file: " + success.getFile());
                            taskIV.setImageURI(Uri.parse(success.getFile().getPath()));
                        },
                        failure -> Log.e(TAG, "Failed to download file: " + failure.getMessage())
                        );
            }
        }


        TextView taskNameTV = findViewById(R.id.DetailTVTaskName);
        TextView taskDescTV = findViewById(R.id.DetailTVTaskDesc);
        TextView taskStatusTV = findViewById(R.id.DetailTVTaskStatus);
        if (taskName != null){
            taskNameTV.setText(taskName);
            taskDescTV.setText(taskDesc);
            taskStatusTV.setText(taskStatus);
        }
    }
}