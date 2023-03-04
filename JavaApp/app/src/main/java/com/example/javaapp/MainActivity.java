package com.example.javaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button Rain = findViewById(R.id.buttonRain);
        TextView moneyValue = findViewById(R.id.editText);

        //Rain.setOnClickListener(v -> moneyValue.setText(R.string.test));
        /*Rain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity","onClick : Make It Rain");
            }
        });*/


    }

    public void showMoney(View view){
        Log.d("MainActivity","onClick : Make It Rain");
    }
}

