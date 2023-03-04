package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.contacts.data.DataHandler;
import com.example.contacts.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> contactArrayList;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.ListView);
        contactArrayList = new ArrayList<>();


        DataHandler db = new DataHandler(MainActivity.this);

       // Contact Aj = new Contact();
        //Aj.setName("Aj");
        //Aj.setPhoneNumber("234568999");

        //db.addContact(Aj);

        //db.addContact(new Contact("James","213986"));
        //db.addContact(new Contact("Greg","098765"));
        //db.addContact(new Contact("Helena","40678765"));
        //db.addContact(new Contact("Carimo","768345"));
        //db.addContact(new Contact("Silo","3445"));
       // db.addContact(new Contact("Santos","6665"));
        //db.addContact(new Contact("Litos","5344"));
        //db.addContact(new Contact("Karate","96534"));
        //db.addContact(new Contact("Guerra","158285"));
        //db.addContact(new Contact("Gema","78130"));

        List<Contact> contactList = db.getAllContacts();
        for (Contact contact : contactList) {
            //Log.d("MainActivity", "onCreate: " + contact.getId() +" "+ contact.getName() + " "+ contact.getPhoneNumber());
            contactArrayList.add(contact.getName());
        }

        //create array adapter
        arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                contactArrayList
        );

        //add to list view
        listView.setAdapter(arrayAdapter);

        //attach event listview

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id){
                Log.d("ListClick","onItemClick "+ position);
            }
        });



    }
}