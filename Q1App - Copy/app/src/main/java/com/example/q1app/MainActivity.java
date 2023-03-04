package com.example.q1app;

import static com.example.q1app.R.layout.activity_main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import com.example.q1app.databinding.ActivityMainBinding;
import com.example.q1app.model.Question;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private final Question[] QB = new Question[]{
            new Question(R.string.Q1, true),
            new Question(R.string.Q2, false),
            new Question(R.string.Q3, true),
            new Question(R.string.Q4, false),
            new Question(R.string.Q5, true),
            new Question(R.string.Q6, false),
            new Question(R.string.Q7, true),
            new Question(R.string.Q8, false)
    };
    private ActivityMainBinding binding;
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        binding = DataBindingUtil.setContentView(this, activity_main);

        binding.questionPanel.setText(QB[0].getAnswerResId());
        binding.truebutton.setOnClickListener(v -> checkAnswer(true));
        binding.falseButton.setOnClickListener(v -> checkAnswer(false));


        binding.nextButton.setOnClickListener(v -> {
            if(index == 7){
                index=0;
            } else{
                index++;
            }
            binding.questionPanel.setText(QB[index].getAnswerResId());
        });

        binding.previousButton.setOnClickListener(v -> {
            if(index == 0){
                index= 7;
            } else {
                index--;
            }
            binding.questionPanel.setText(QB[index].getAnswerResId());
        });


    }

    public void checkAnswer(boolean userAns){
        boolean CorrectAnswer = QB[index].isAnswerTrue();
        int msgId;
        if(userAns==CorrectAnswer){
            msgId=R.string.correct;
        }else {
            msgId=R.string.incorrect;
        }
        Snackbar.make(binding.imageView, msgId, Snackbar.LENGTH_SHORT).show();

    }
}

