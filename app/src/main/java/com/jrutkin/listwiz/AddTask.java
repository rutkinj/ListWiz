package com.jrutkin.listwiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Button addTaskButton = AddTask.this.findViewById(R.id.AddButton);
        addTaskButton.setOnClickListener(view ->{
            TextView title = AddTask.this.findViewById(R.id.AddTVTitle);
            title.setText("Submitted but I don't how to do a toast");
        });
    }

}