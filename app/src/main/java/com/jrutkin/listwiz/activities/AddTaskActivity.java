package com.jrutkin.listwiz.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.StatusEnum;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.TaskOwner;
import com.jrutkin.listwiz.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AddTaskActivity extends AppCompatActivity {

    public static final String TAG = "AddTaskActivity";

    Spinner statusSpinner;
    Spinner teamSpinner;
    CompletableFuture<List<TaskOwner>> taskOwnersFuture = null;
    ActivityResultLauncher<Intent> activityResultLauncher;
    private String s3ImageKey = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        activityResultLauncher = getImagePickingActivityResultLauncher();

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
        addImageButtonSetup();
        statusSpinnerSetup();
        buttonSetup();
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
                    .s3ImageKey(s3ImageKey)
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

    public void addImageButtonSetup(){
        findViewById(R.id.AddTaskButtonAddImage).setOnClickListener(view -> {
            launchImageSelectionIntent();
        });
    }

    public void launchImageSelectionIntent(){
        Intent goToImageSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
        goToImageSelectionIntent.setType("*/*");// need to specify filetype, even if it's just double wildcard lol
        goToImageSelectionIntent.putExtra(Intent.EXTRA_MIME_TYPES, new String[]{"image/jpeg", "image/png"});
        activityResultLauncher.launch(goToImageSelectionIntent);
    }

    private ActivityResultLauncher<Intent> getImagePickingActivityResultLauncher(){
        ActivityResultLauncher<Intent> imagePickingActivityResultLauncher =
            registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        Uri pickedImageFileUri = result.getData().getData();
                        try {
                            InputStream pickedImageInputStream = getContentResolver().openInputStream(pickedImageFileUri);
                            String pickedImageFilename = getFileNameFromUri(pickedImageFileUri);
                            Log.i(TAG, "Something input stream. Filename: " + pickedImageFilename);
                            uploadInputStreamToS3(pickedImageInputStream, pickedImageFilename, pickedImageFileUri);
                        } catch (FileNotFoundException fileNotFoundException){
                            Log.e(TAG, "Failed to get file from file picker: " + fileNotFoundException.getMessage());
                        }
                    }
            );
        return imagePickingActivityResultLauncher;
    }

    private void uploadInputStreamToS3(InputStream pickedImageInputStream, String pickedImageFilename, Uri pickedImageFileUri){
        Amplify.Storage.uploadInputStream(
                pickedImageFilename,
                pickedImageInputStream,
                success -> {
                    Log.i(TAG, "Successfully uploaded to S3");
                    s3ImageKey = success.getKey();
                    ImageView uploadedImage = findViewById(R.id.AddTaskButtonAddImage);
                    InputStream pickedImageInputStreamCopy = null;
                    try {
                        pickedImageInputStreamCopy = getContentResolver().openInputStream(pickedImageFileUri);
                    } catch (FileNotFoundException fileNotFoundException){
                        Log.e(TAG, "Couldn't copy image stream: " + fileNotFoundException.getMessage());
                    }
                    uploadedImage.setImageBitmap(BitmapFactory.decodeStream(pickedImageInputStreamCopy));
                },
                failure -> Log.e(TAG, "Failed to upload file to S3: " + pickedImageFilename + failure.getMessage())
        );
    }

    private void saveTask(){

    }

    // Taken from https://stackoverflow.com/a/25005243/16889809
    @SuppressLint("Range")
    public String getFileNameFromUri(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
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