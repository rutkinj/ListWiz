package com.jrutkin.listwiz.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jrutkin.listwiz.R;
import com.jrutkin.listwiz.activities.MainActivity;
import com.jrutkin.listwiz.activities.TaskDetailActivity;
import com.jrutkin.listwiz.models.TaskModel;

import java.util.ArrayList;
import java.util.List;

public class TaskRecyclerViewAdapter extends RecyclerView.Adapter<TaskRecyclerViewAdapter.TaskViewHolder> {
    List<TaskModel> taskList;
    Context callingActivity;

    public TaskRecyclerViewAdapter(List<TaskModel> taskList, Context callingActivity) {
        this.taskList = taskList;
        this.callingActivity = callingActivity;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View taskFragment = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task, parent, false);
        return new TaskViewHolder(taskFragment);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {

        TextView taskFragTVTaskName = holder.itemView.findViewById(R.id.TaskFragTVTaskName);
        String taskName = taskList.get(position).getTaskName() + ": ";
        taskFragTVTaskName.setText(taskName);

        TextView taskFragTVTaskDesc = holder.itemView.findViewById(R.id.TaskFragTVTaskDesc);
        String taskDesc = taskList.get(position).getTaskDesc();
        taskFragTVTaskDesc.setText(taskDesc);

        TextView taskFragTVTaskStatus = holder.itemView.findViewById(R.id.TaskFragTVTaskDesc);
        String taskStatus = taskList.get(position).getTaskStatus().toString();
        taskFragTVTaskDesc.setText(taskDesc);

        View taskItemView = holder.itemView;
        taskItemView.setOnClickListener(v -> {
            Intent goToTaskDetail = new Intent(callingActivity, TaskDetailActivity.class);
            goToTaskDetail.putExtra(MainActivity.TASK_NAME_TAG, taskName);
            goToTaskDetail.putExtra(MainActivity.TASK_DESC_TAG, taskDesc);
            goToTaskDetail.putExtra(MainActivity.TASK_STATUS_TAG, taskStatus);
            callingActivity.startActivity(goToTaskDetail);
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder{

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
