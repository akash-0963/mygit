package com.example.rd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rd.model.Contact;
import com.example.rd.model.ContactViewModel;

public class NewContact extends AppCompatActivity {

    public static final String NAME_REPLY = "name_reply";
    public static final String OCCUPATION = "occupation";
    private EditText enterName;
    private EditText enterOccupation;
    private Button saveButton;
    private ContactViewModel contactViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        enterName = findViewById(R.id.editText_Name);
        enterOccupation = findViewById(R.id.editText_Occupation);
        saveButton = findViewById(R.id.button_Save);

        contactViewModel = new ViewModelProvider.AndroidViewModelFactory(NewContact.this
                .getApplication())
                .create(ContactViewModel.class);

        saveButton.setOnClickListener(v -> {

            Intent replyIntent = new Intent();


            if(!TextUtils.isEmpty(enterName.getText())
                    && !TextUtils.isEmpty(enterOccupation.getText())) {
                /*Contact contact = new Contact(enterName.getText().toString(), enterOccupation.getText().toString());

                ContactViewModel.insert(contact);*/

                String name = enterName.getText().toString();
                String occupation = enterOccupation.getText().toString();

                replyIntent.putExtra(NAME_REPLY,name);
                replyIntent.putExtra(OCCUPATION,occupation);

                setResult(RESULT_OK,replyIntent);

            }else {
                /*Toast.makeText(this,R.string.empty,Toast.LENGTH_SHORT)
                        .show();*/
                setResult(RESULT_CANCELED,replyIntent);
            }
            finish();
        });
    }
}