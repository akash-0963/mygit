package com.example.contactsmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.contactsmanager.data.DataBaseHandler;
import com.example.contactsmanager.model.Contact;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataBaseHandler db = new DataBaseHandler(MainActivity.this);

        Contact jason = new Contact();
        jason.setName("jason");
        jason.setPhonenum("5678997");

        db.addContact(jason);

        List<Contact> contactList = db.getAllContacts();

        for (Contact c: contactList) {
            Log.d("TAG", "OnCreate " + c.getName());
        }

    }
}