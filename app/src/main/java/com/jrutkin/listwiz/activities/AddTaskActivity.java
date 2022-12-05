package com.jrutkin.listwiz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.jrutkin.listwiz.R;

public class AddTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        buttonSetup();
    }

    private void buttonSetup(){
        Button addTaskButton = AddTaskActivity.this.findViewById(R.id.AddButton);
        addTaskButton.setOnClickListener(view ->{
            Context context = getApplicationContext();
            CharSequence submitMsg = "Task Submitted";
            Toast toast = Toast.makeText(context, submitMsg, Toast.LENGTH_SHORT);
            toast.show();
        });
    }
}