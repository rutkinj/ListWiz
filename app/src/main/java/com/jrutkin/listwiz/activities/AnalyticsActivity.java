package com.jrutkin.listwiz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.amplifyframework.analytics.AnalyticsEvent;
import com.amplifyframework.core.Amplify;
import com.jrutkin.listwiz.R;

import java.util.Date;

public class AnalyticsActivity extends AppCompatActivity {
    private static final String TAG = "AnalyticsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);

        AnalyticsEvent event = AnalyticsEvent.builder()
                .name("Opened activity")
                .addProperty("Time", Long.toString(new Date().getTime()))
                .build();
        Log.i(TAG, "made analytics event");
        Amplify.Analytics.recordEvent(event);
    }
}