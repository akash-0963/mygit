package com.example.contactsmanager.model;

public class Contact {
    private int id;
    private String Name;
    private String Phonenum;

    public Contact(){
    }

    public Contact(int id, String name, String phonenum) {
        this.id = id;
        Name = name;
        Phonenum = phonenum;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getPhonenum() {
        return Phonenum;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPhonenum(String phonenum) {
        Phonenum = phonenum;
    }
}
