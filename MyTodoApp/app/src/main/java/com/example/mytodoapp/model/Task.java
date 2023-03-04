package com.example.mytodoapp.model;

import com.example.mytodoapp.util.Priority;

import java.util.Date;

public class Task {
    private int id;
    private String taskName;
    //private String taskDesc;
   // private Priority priority;
   // private Date dueDate;
    //private Date dateCreated;
    private Boolean taskStatus;

    public Task(int id, String taskName, Boolean taskStatus) {
        this.id = id;
        this.taskName = taskName;
        //this.taskDesc = taskDesc;
        this.taskStatus = taskStatus;

        //this.priority = priority;
        //this.dueDate = dueDate;
        //this.dateCreated = dateCreated;
    }

    public Task(String taskName, Boolean taskStatus) {
        this.taskName = taskName;
        //this.taskDesc = taskDesc;
        this.taskStatus = taskStatus;
    }

    public Task() {

    }

    public int getId() {
        return id;
    }

    public String getTaskName() {
        return taskName;
    }

    /*public String getTaskDesc() {
        return taskDesc;
    }*/

   /* public Priority getPriority() {
        return priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }*/

    public void setId(int id) {
        this.id = id;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /*public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }*/

    public Boolean getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Boolean taskStatus) {
        this.taskStatus = taskStatus;
    }

   /* public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }*/
}
