package com.example.rd.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.rd.model.Contact;

import java.util.List;


@Dao
public interface ContactDAO {

    //CRUD

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Contact contact);

    @Query("DELETE FROM CONTACT_TABLE")
    void deleteAll();

    @Query("SELECT * FROM CONTACT_TABLE")
    LiveData<List<Contact>> getAllContacts();

}
