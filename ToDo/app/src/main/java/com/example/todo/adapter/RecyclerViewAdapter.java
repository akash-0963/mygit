package com.example.todo.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.AddNewTask;
import com.example.todo.R;
import com.example.todo.data.DatabaseHandler;
import com.example.todo.model.Task;
import com.example.todo.util.Util;

import java.util.List;
import java.util.Objects;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Task> taskList;
    private Context context;
    private DatabaseHandler db;
    private MainActivity activity;

    public RecyclerViewAdapter(DatabaseHandler db, Context context) {
        this.db = db;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = Objects.requireNonNull(taskList.get(position));
        holder.tName.setText(task.getTaskName());
        //holder.tDesc.setText(task.getTaskDesc());
        holder.tStatus.setChecked(task.getTaskStatus());

        holder.tStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    db.updateTaskStatus(task.getId(),true);
                }else {
                    db.updateTaskStatus(task.getId(),false);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public Context getContext() {
        return activity;
    }

    public void setTaskList(List<Task> tasks){
        taskList = tasks;
    }

    public void deleteTask(int position){
        Task task = taskList.get(position);
        db.deleteTask(task.getId());
        taskList.remove(position);
        notifyItemRemoved(position);
    }

    public void editTask(int position){
        Task task = taskList.get(position);

        Bundle bundle = new Bundle();

        bundle.putInt(Util.KEY_ID,task.getId());
        bundle.putString(Util.KEY_TASK_NAME,task.getTaskName());

        AddNewTask task1 = new AddNewTask();
        task1.setArguments(bundle);
        task1.show(activity.getSupportFragmentManager(),task1.getTag());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tName;
        //public TextView tDesc;
        public CheckBox tStatus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tStatus = itemView.findViewById(R.id.checkBox);
        }
    }
}

