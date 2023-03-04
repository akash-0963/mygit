package com.example.mytodoapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.lifecycle.LiveData;

import com.example.mytodoapp.R;
import com.example.mytodoapp.model.Task;
import com.example.mytodoapp.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Create table query
        String CREATE_TASK_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "(" +
                Util.KEY_ID + " INTEGER PRIMARY KEY," +
                Util.KEY_TASK_NAME + " TEXT," +
                /*Util.KEY_TASK_DESC + " TEXT," +*/
                Util.KEY_TASK_STATUS + " BOOLEAN NOT NULL DEFAULT 'FALSE'" + ")";
               /* Util.KEY_PRIORITY + " TEXT CHECK( " + Util.KEY_PRIORITY + " IN ('HIGH','MEDIUM','LOW') )   NOT NULL DEFAULT 'LOW'," +
                Util.KEY_DUE_DATE + " NUMERIC," +
                Util.KEY_DATE_CREATED + " NUMERIC" + */

        db.execSQL(CREATE_TASK_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //delete complete table
        String DROP_TABLE = String.valueOf(R.string.db_drop);
        db.execSQL(DROP_TABLE, new String[]{Util.TABLE_NAME});

        //create again
        onCreate(db);
    }


    public void addTask(Task task){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util.KEY_TASK_NAME,task.getTaskName());
        //values.put(Util.KEY_TASK_DESC,task.getTaskDesc());
        values.put(Util.KEY_TASK_STATUS,task.getTaskStatus());

        db.insert(Util.TABLE_NAME,null,values);
        //Log.d("In Add method","we are in addTask");

        db.close();
    }

    public Task getTask(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Util.TABLE_NAME,
                new String[]{Util.KEY_ID,Util.KEY_TASK_NAME,Util.KEY_TASK_STATUS},
                Util.KEY_ID + "=?", new String[]{String.valueOf(id)},
                null,null,null);

        if(cursor !=null)
            cursor.moveToFirst();

        Task task = new Task();
        task.setId(Integer.parseInt(cursor.getString(0)));
        task.setTaskName(cursor.getString(1));
        //task.setTaskDesc(cursor.getString(2));
        task.setTaskStatus(Boolean.parseBoolean(cursor.getString(3)));

        return task;
    }

    public List<Task> getAllTasks(){
        List<Task> taskList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectAll = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);

        if (cursor.moveToFirst()){
            do {
                Task task = new Task();
                task.setId(Integer.parseInt(cursor.getString(0)));
                task.setTaskName(cursor.getString(1));
                //task.setTaskDesc(cursor.getString(2));
                task.setTaskStatus(Boolean.parseBoolean(cursor.getString(2)));

                //add contact object to list
                taskList.add(task);
             } while (cursor.moveToNext());
        }

        return taskList;
    }

    public List<Task> getCompletedTasks(){
        List<Task> cTaskList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectAll = "SELECT * FROM " + Util.TABLE_NAME + " WHERE " + Util.KEY_TASK_STATUS + " == true";
        Cursor cursor = db.rawQuery(selectAll,null);

        if (cursor.moveToFirst()){
            do {
                Task task = new Task();
                task.setId(Integer.parseInt(cursor.getString(0)));
                task.setTaskName(cursor.getString(1));
                //task.setTaskDesc(cursor.getString(2));
                task.setTaskStatus(Boolean.parseBoolean(cursor.getString(3)));

                //add contact object to list
                cTaskList.add(task);
            } while (cursor.moveToNext());
        }

        return cTaskList;
    }

    public List<Task> getUncompletedTasks(){
        List<Task> uTaskList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectAll = "SELECT * FROM " + Util.TABLE_NAME + " WHERE " + Util.KEY_TASK_STATUS + " == false";
        Cursor cursor = db.rawQuery(selectAll,null);

        if (cursor.moveToFirst()){
            do {
                Task task = new Task();
                task.setId(Integer.parseInt(cursor.getString(0)));
                task.setTaskName(cursor.getString(1));
                //task.setTaskDesc(cursor.getString(2));
                task.setTaskStatus(Boolean.parseBoolean(cursor.getString(2)));// column Index 3

                //add contact object to list
                uTaskList.add(task);
            } while (cursor.moveToNext());
        }

        return uTaskList;
    }

    public int updateTaskStatus(int id, Boolean status){
        SQLiteDatabase db = this.getWritableDatabase();

        Task task = getTask(id);
        ContentValues values = new ContentValues();

        values.put(Util.KEY_TASK_NAME,task.getTaskName());
        //values.put(Util.KEY_TASK_DESC,task.getTaskDesc());
        values.put(Util.KEY_TASK_STATUS,status);

        return db.update(Util.TABLE_NAME,
                values,
                Util.KEY_ID + "=?",
                new String[]{String.valueOf(id)});
    }

    public int updateTask(int id, String task){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Util.KEY_TASK_NAME,task);
        //values.put(Util.KEY_TASK_DESC,task.getTaskDesc());
        //values.put(Util.KEY_TASK_STATUS,task.getTaskStatus());

        return db.update(Util.TABLE_NAME,
                values,
                Util.KEY_ID + "=?",
                new String[]{String.valueOf(id)});
    }

    public void deleteTask(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(Util.TABLE_NAME,Util.KEY_ID + "=?" ,new String[]{String.valueOf(id)});
        db.close();
    }
}
