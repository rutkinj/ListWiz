package com.jrutkin.listwiz.activities.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.jrutkin.listwiz.R;
import com.jrutkin.listwiz.activities.MainActivity;

public class SignInActivity extends AppCompatActivity {
    public static final String TAG = "SignInActivity";
    Intent callingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        callingIntent = getIntent();

        signInSetup();
    }

    public void signInSetup(){
        //follow thru from auth
//        String userEmail = callingIntent.getStringExtra(SignUpActivity.SIGNUP_EMAIL_TAG);

        // find and set email ET
        findViewById(R.id.SignInButton).setOnClickListener(view -> {
            String userEmail = ((EditText)findViewById(R.id.SignInETEmail)).getText().toString();
            String userPassword = ((EditText)findViewById(R.id.SignInETPassword)).getText().toString();
            Amplify.Auth.signIn(
                userEmail,
                userPassword,
                success -> {
                    Log.i(TAG, "SignIn success: " + success.toString());
                    Intent goToMainActivity = new Intent(this, MainActivity.class);
                    startActivity(goToMainActivity);
                },
                failure -> {
                    Log.w(TAG, "SignIn failure: " + failure.toString());
                    runOnUiThread(() -> Toast.makeText(this, "Sign In failed", Toast.LENGTH_SHORT).show());
                }
            );
        });

    }
}