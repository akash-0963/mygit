package com.example.showmyname;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button showButton = findViewById(R.id.button);
        TextView nameText = findViewById(R.id.textView);
        EditText enterName = findViewById(R.id.editText);

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = enterName.getText().toString();
                if (name.isEmpty()) {
                    nameText.setText("Please Enter your Name");
                } else {
                    nameText.setText("Hello, " + name);
                }
            }
        });

    }
}