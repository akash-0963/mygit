package com.example.contactroom.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.contactroom.data.ContactDAO;
import com.example.contactroom.model.Contact;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Contact.class},version = 1,exportSchema = false)
public abstract class ContactRoomDatabase extends RoomDatabase {

    public abstract ContactDAO contactDAO();
    public static final int NUMBER_OF_THREADS = 4;


    public static volatile ContactRoomDatabase INSTANCE;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ContactRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (ContactRoomDatabase.class){
                if(INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ContactRoomDatabase.class,"contact_database").
                            addCallback(sRoomDatabaseCallback).
                            build();
                }
            }
        }

        return INSTANCE;
    }

    public static final RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);

                    databaseWriteExecutor.execute(() ->{
                        ContactDAO contactDAO = INSTANCE.contactDAO();
                        contactDAO.deleteAll();

                        Contact contact = new Contact("Akash", "Student");
                        contactDAO.insert(contact);

                        contact = new Contact("Sandy","Student");
                        contactDAO.insert(contact);
                    });
                }
            };
}
