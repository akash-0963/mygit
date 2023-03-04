package com.example.contactroom.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.contactroom.data.ContactRepository;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {

    public static ContactRepository repo;
    public final LiveData<List<Contact>> allContacts;

    public ContactViewModel(@NonNull Application application) {
        super(application);

        repo = new ContactRepository(application);
        allContacts = repo.getAllData();
    }

    public LiveData<List<Contact>> getAllContacts() {
        return allContacts;
    }

    public static void insert(Contact contact){
        repo.insert(contact);
    }
}
