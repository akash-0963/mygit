package com.example.contactsmanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.contactsmanager.R;
import com.example.contactsmanager.model.Contact;
import com.example.contactsmanager.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {


    public DataBaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }


    //We create our table
    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQL - structured query language


        //Query String
        //create table_name( id, name, phonenum)
        String CREATE_CONTACT_NAME = "CREATE TABLE "+ Util.TABLE_NAME + "( "
                + Util.KEY_ID + " INTEGER PRIMARY KEY," + Util.KEY_NAME + "TEXT,"
                + Util.KEY_PHONENUM + "TEXT" + ")";


        //query execution
        db.execSQL(CREATE_CONTACT_NAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = String.valueOf(R.string.db_drop);

        db.execSQL(DROP_TABLE,new String[]{Util.DATABASE_NAME});

        //create table again
        onCreate(db);
    }

    //Creating CRUD Functionalities/Operations

    //Add contacts
    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME,contact.getName());
        values.put(Util.KEY_PHONENUM,contact.getPhonenum());

        //Insert to the row
        db.insert(Util.TABLE_NAME,null,values);

        //closing the db connection
        db.close();
    }

    //get a contact
    public Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Util.TABLE_NAME,
                new String[]{Util.KEY_ID, Util.KEY_NAME, Util.KEY_PHONENUM},
                Util.KEY_ID +"=?", new String[]{String.valueOf(id)},
                null, null, null
        );


        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact();
        contact.setId(Integer.parseInt(cursor.getString(0)));
        contact.setName(cursor.getString(1));
        contact.setPhonenum(cursor.getString(2));

        Log.d("get","incontacts");

        return contact;

    }

    //get all contacts
    public List<Contact> getAllContacts(){
        List<Contact> contactList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectAll = "SELECT * FROM contacts";

        Cursor cursor = db.rawQuery(selectAll,null);

        if (cursor.moveToFirst()){
            do {
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhonenum(cursor.getString(2));

                //add to list
                contactList.add(contact);
            }while (cursor.moveToNext());
        }

        //Log
        //Log.d("getAllContact","All item Got");
        return contactList;
    }

}
