package com.jrutkin.listwiz.activities.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.amplifyframework.core.Amplify;
import com.jrutkin.listwiz.R;

public class SignInActivity extends AppCompatActivity {
    public static final String TAG = "signInActivity";
    Intent callingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        callingIntent = getIntent();
    }

    public void signInSetup(){
        //follow thru from auth
//        String userEmail = callingIntent.getStringExtra(SignUpActivity.SIGNUP_EMAIL_TAG);

        // find and set email ET
        // on click () -> {
        String userEmail
        String userPassword
        Amplify.Auth.signIn(
                userEmail,
                userPassword,
                success -> Log.i(TAG, "SignIn success: " + success.toString()),
                failure -> Log.w(TAG, "SignIn failure: " + failure.toString())
        );
    }
}