package com.jrutkin.listwiz.activities.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.jrutkin.listwiz.R;

public class VerifySignUpActivity extends AppCompatActivity {
    public static final String TAG = "VerifySignUpActivity";
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
        findViewById(R.id.VerifyButtonSubmit).setOnClickListener(view -> {
            String verificationCode = ((EditText)findViewById(R.id.VerifyETCode)).getText().toString();
                Amplify.Auth.confirmSignUp(
                    userEmail,
                    verificationCode,
                    success -> {
                        Log.i(TAG, "Verification success: " + success);
                        Intent goToSignInActivity = new Intent(this, SignUpActivity.class);
                        goToSignInActivity.putExtra(SignUpActivity.SIGNUP_EMAIL_TAG, userEmail);
                        startActivity(goToSignInActivity);
                    },
                    failure -> {
                        Log.w(TAG, "Verification failure: " + failure);
                        runOnUiThread(() -> Toast.makeText(VerifySignUpActivity.this, "Failed to verify", Toast.LENGTH_SHORT));
                    }
                );
        });
    }
}