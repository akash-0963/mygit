package com.example.q1app;

import static com.example.q1app.R.layout.activity_main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

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
        binding.truebutton.setOnClickListener(v -> saveAnswer(true));
        binding.falseButton.setOnClickListener(v -> saveAnswer(false));


        binding.nextButton.setOnClickListener(v -> {
            if(index == 6) {
                binding.nextButton.setVisibility(View.INVISIBLE);
                binding.submitbutton.setVisibility(View.VISIBLE);
            }
            index++;
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

        binding.submitbutton.setOnClickListener(v -> {
            checkAnswer();
            binding.submitbutton.setVisibility(View.INVISIBLE);
            binding.previousButton.setVisibility(View.INVISIBLE);
            binding.truebutton.setVisibility(View.INVISIBLE);
            binding.falseButton.setVisibility(View.INVISIBLE);
            binding.questionPanel.setVisibility(View.INVISIBLE);
            binding.retrybutton.setVisibility(View.VISIBLE);
        });

        binding.retrybutton.setOnClickListener(v -> {
            binding.questionPanel.setText(QB[0].getAnswerResId());
            binding.previousButton.setVisibility(View.VISIBLE);
            binding.nextButton.setVisibility(View.VISIBLE);
            binding.truebutton.setVisibility(View.VISIBLE);
            binding.falseButton.setVisibility(View.VISIBLE);
            binding.questionPanel.setVisibility(View.VISIBLE);
            binding.retrybutton.setVisibility(View.INVISIBLE);
        });


    }
    public void saveAnswer(boolean answer){
        QB[index].setuserAnswer(answer);
    }

    public void checkAnswer(){
        int count = 0;

        for(int i = 0;i<8;i++) {
            if (QB[index].isAnswer() == QB[index].isuserAnswer()) {
                count++;
            }
        }

        String msgID = String.format("Your Score : %d", count);

        Snackbar.make(binding.imageView, msgID, Snackbar.LENGTH_SHORT).show();

    }
}

