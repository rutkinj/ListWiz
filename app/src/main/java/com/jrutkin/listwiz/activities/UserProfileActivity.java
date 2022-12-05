package com.jrutkin.listwiz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jrutkin.listwiz.R;

public class UserProfileActivity extends AppCompatActivity {

    SharedPreferences sharedPrefs;
    public static final String USERNAME_TAG = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        saveToSharedPrefs();
    }

    public void saveToSharedPrefs(){
        SharedPreferences.Editor prefEditor = sharedPrefs.edit();
        Button submitButton = UserProfileActivity.this.findViewById(R.id.UserProfileSubmitButton);

        submitButton.setOnClickListener(view -> {
            EditText usernameET = findViewById(R.id.UserProfileETUsername);
            String usernameText = usernameET.getText().toString();
            prefEditor.putString(USERNAME_TAG, usernameText);

            prefEditor.apply();

            Toast.makeText(this, "Username Saved", Toast.LENGTH_SHORT).show();
        });
    }
}