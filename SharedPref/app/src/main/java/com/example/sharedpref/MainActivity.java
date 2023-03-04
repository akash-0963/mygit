package com.example.sharedpref;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String MESSAGE_ID = "messages_prefs";
    private EditText msgField;
    private TextView enteredMsg;
    private Button saveButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        msgField = findViewById(R.id.edittext);
        enteredMsg = findViewById(R.id.message);
        saveButton = findViewById(R.id.button);

        saveButton.setOnClickListener(v -> {
            String msg = msgField.getText().toString().trim();

            SharedPreferences sharedPreferences = getSharedPreferences(MESSAGE_ID,MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("message",msg);

            editor.apply();  //saving to disk

        });


        SharedPreferences getSharedData = getSharedPreferences(MESSAGE_ID,MODE_PRIVATE);
        String value = getSharedData.getString("message","Nothing Yet");

        enteredMsg.setText(value);

    }
}