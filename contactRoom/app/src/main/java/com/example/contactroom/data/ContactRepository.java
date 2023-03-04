package com.example.contactroom.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.contactroom.model.Contact;
import com.example.contactroom.util.ContactRoomDatabase;

import java.util.List;

public class ContactRepository {
    private ContactDAO contactDAO;
    private LiveData<List<Contact>> allContacts;

    public ContactRepository(Application application){
        ContactRoomDatabase db = ContactRoomDatabase.getDatabase(application);
        contactDAO = db.contactDAO();

        allContacts = contactDAO.getAllContacts();
    }

    public LiveData<List<Contact>> getAllData(){
        return allContacts;
    }

    public void insert(Contact contact){
        ContactRoomDatabase.databaseWriteExecutor.execute(()->{
            contactDAO.insert(contact);
        });
    }
}
