package com.example.makeitrain;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView MoneyValue;
    int Amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button click = findViewById(R.id.button);
        MoneyValue = findViewById(R.id.amount_tab);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amount = Integer.parseInt(MoneyValue.getText().toString());
                Amount += 1000;
                MoneyValue.setText(String.valueOf(Amount));
            }
        });
    }
}