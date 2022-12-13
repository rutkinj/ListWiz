package com.jrutkin.listwiz.activities.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.jrutkin.listwiz.R;

public class SignUpActivity extends AppCompatActivity {
    public static final String TAG = "signUpActivity";
    public static final String SIGNUP_EMAIL_TAG = "signUpEmail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //setup form
    }

    public void signUpFormSetup(){
//        findViewById(bing bong here the button) //////////////////////////////////
        String userEmail = "bingbong"; //get from edit text doot doot
        String userPassword = "dootdoot"; //get from edit text doot doot
        Amplify.Auth.signUp(userEmail,
                userPassword,
                AuthSignUpOptions.builder()
                        .userAttributes(AuthUserAttributeKey.email(), userEmail)
                        .build(),
                success -> {
                    Log.i(TAG, "Sign Up Success: " + success);
                    Intent goToVeifyActi = new Intent(this, VerifySignUpActivity.class);
                    goToVeifyActi.putExtra(SIGNUP_EMAIL_TAG, userEmail);
                    startActivity(goToVeifyActi);
                },
                failure -> {
                    Log.w(TAG, "Sign Up Failure: " + failure);
                    runOnUiThread(() -> Toast.makeText(this,"Failed to signup", Toast.LENGTH_SHORT).show());
                }

        );
    }
}