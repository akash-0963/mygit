package com.example.mytodoapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mytodoapp.data.DatabaseHandler;
import com.example.mytodoapp.model.Task;
import com.example.mytodoapp.util.OnDialogCloseListener;
import com.example.mytodoapp.util.Util;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddNewTask extends BottomSheetDialogFragment {

    public  static final String TAG = "AddNewTask";

    private EditText editText;
    private Button button;
    private DatabaseHandler db;


    public static AddNewTask newInstance(){
        return new AddNewTask();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_new_task,container,false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editText = view.findViewById(R.id.editText);
        button = view.findViewById(R.id.button_save);

        db = new DatabaseHandler(getActivity());

        boolean isUpdate = false;

        Bundle bundle = getArguments();
        if (bundle != null){
            isUpdate = true;
            String task = bundle.getString(Util.KEY_TASK_NAME);
            editText.setText(task);

            if (task.length() > 0){
                button.setEnabled(false);
            }
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")){
                    button.setEnabled(false);
                    button.setBackgroundColor(Color.GRAY);
                }else {
                    button.setEnabled(true);
                    button.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.design_default_color_primary));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        boolean finalIsUpdate = isUpdate;
        button.setOnClickListener(v -> {
            String text = editText.getText().toString();

            if (finalIsUpdate){
                db.updateTask(bundle.getInt("id"),text);
            }else {
                Task task = new Task();
                task.setTaskName(text);
                task.setTaskStatus(false);
                db.addTask(task);
            }
            dismiss();
        });
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Activity activity = getActivity();

        if (activity instanceof OnDialogCloseListener){
            ((OnDialogCloseListener)activity).onDialogClose(dialog);
        }
    }
}
