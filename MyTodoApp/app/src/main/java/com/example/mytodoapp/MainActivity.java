package com.example.mytodoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.audiofx.Visualizer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.mytodoapp.adapter.RecyclerViewAdapter;
import com.example.mytodoapp.data.DatabaseHandler;
import com.example.mytodoapp.model.Task;
import com.example.mytodoapp.util.OnDialogCloseListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnDialogCloseListener {

    private List<Task> taskList;
    private RecyclerViewAdapter adapter;
    private DatabaseHandler db;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recycler_view);
        taskList = new ArrayList<>();
        adapter = new RecyclerViewAdapter(db,MainActivity.this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        adapter.setTaskList(taskList);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTask.newInstance().show(getSupportFragmentManager(),AddNewTask.TAG);
            }
        });
    }

    @Override
    public void onDialogClose(DialogInterface dialogInterface) {
        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        adapter.setTaskList(taskList);
        adapter.notifyDataSetChanged();
    }
}