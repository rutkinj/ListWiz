package com.jrutkin.listwiz.activities.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.amplifyframework.core.Amplify;
import com.jrutkin.listwiz.R;

public class VerifySignUpActivity extends AppCompatActivity {
    public static final String TAG = "verifySignUpActivity";
    Intent callingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_sign_up);
        callingIntent = getIntent();

        verifyFormSetup();
    }

    public void verifyFormSetup(){
        String userEmail = callingIntent.getStringExtra(SignUpActivity.SIGNUP_EMAIL_TAG);
        // get the button () -> {
        String verificationCode = 12//get the confirmation code
                Amplify.Auth.confirmSignUp(userEmail, verificationCode,
                        success -> Log.i(TAG, "Confirm-SignUp success: " + success.toString()),
                        failure -> Log.w(TAG, "Confirm-SignUp failure: " + failure.toString())
                );
    }
}