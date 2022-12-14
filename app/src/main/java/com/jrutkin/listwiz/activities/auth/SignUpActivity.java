package com.jrutkin.listwiz.activities.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.jrutkin.listwiz.R;

public class SignUpActivity extends AppCompatActivity {
    public static final String TAG = "SignUpActivity";
    public static final String SIGNUP_EMAIL_TAG = "signUpEmail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUpFormSetup();
    }

    public void signUpFormSetup(){
        findViewById(R.id.SignUpButtonSubmit).setOnClickListener(view -> {
            String userEmail = ((EditText)findViewById(R.id.SignUpETEmail)).getText().toString();
            String userPassword = ((EditText)findViewById(R.id.SignUpETPassword)).getText().toString();
            Amplify.Auth.signUp(
                userEmail,
                userPassword,
                AuthSignUpOptions.builder()
                        .userAttribute(AuthUserAttributeKey.email(), userEmail)
                        .build(),
                success -> {
                    Log.i(TAG, "Sign Up Success: " + success);
                    Intent goToVerifySignUpActivity = new Intent(this, VerifySignUpActivity.class);
                    goToVerifySignUpActivity.putExtra(SIGNUP_EMAIL_TAG, userEmail);
                    startActivity(goToVerifySignUpActivity);
                },
                failure -> {
                    Log.w(TAG, "Sign Up Failure: " + failure);
                    runOnUiThread(() -> Toast.makeText(this,"Failed to signup", Toast.LENGTH_SHORT).show());
                }
            );
        });
    }
}