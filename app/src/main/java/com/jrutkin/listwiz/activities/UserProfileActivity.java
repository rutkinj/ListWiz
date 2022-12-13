package com.jrutkin.listwiz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.TaskOwner;
import com.jrutkin.listwiz.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class UserProfileActivity extends AppCompatActivity {

    SharedPreferences sharedPrefs;
    public static final String TAG = "UserProfileActivity";
    public static final String USERNAME_TAG = "username";
    public static final String USER_TEAM_TAG = "userTeam";

    Spinner teamSpinner;
    CompletableFuture<List<TaskOwner>> taskOwnersFuture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

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

        saveToSharedPrefs();
    }

    public void saveToSharedPrefs(){
        SharedPreferences.Editor prefEditor = sharedPrefs.edit();
        Button submitButton = UserProfileActivity.this.findViewById(R.id.UserProfileSubmitButton);

        submitButton.setOnClickListener(view -> {
            EditText usernameET = findViewById(R.id.UserProfileETUsername);
            Spinner teamSpinner = findViewById(R.id.UserProfileSpinnerTeam);
            String usernameText = usernameET.getText().toString();
            String userTeamText = teamSpinner.getSelectedItem().toString();
            prefEditor.putString(USERNAME_TAG, usernameText);
            prefEditor.putString(USER_TEAM_TAG, userTeamText);

            prefEditor.apply();

            Toast.makeText(this, "Username Saved", Toast.LENGTH_SHORT).show();
        });
    }

    public void teamSpinnerSetup(ArrayList<String> ownerNames){
        teamSpinner = findViewById(R.id.UserProfileSpinnerTeam);
        teamSpinner.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                ownerNames
        ));
    }
}