package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Controller.AppController;
import com.example.myapplication.data.Repository;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.model.Question;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    List<Question> questionList;
    private int currentQuestionIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        questionList = new Repository().getQuestions(questionArrayList -> {
                    binding.quePanel.setText(questionArrayList.get(currentQuestionIndex)
                            .getAnswer());
                    binding.queNoPanel.setText(String.format(getString(R.string.text_formatted),
                        currentQuestionIndex + 1, questionArrayList.size()));
                }
        );

        binding.buttonNext.setOnClickListener(view -> {
            currentQuestionIndex = (currentQuestionIndex + 1) % questionList.size();
            updateQuestion();
        });

        binding.buttonPrevious.setOnClickListener(view -> {
            if(currentQuestionIndex == 0){
                currentQuestionIndex = questionList.size() - 1;
            }else{
                currentQuestionIndex = (currentQuestionIndex - 1) % questionList.size();
            }
            updateQuestion();
        });

        binding.buttonTrue.setOnClickListener(v -> {
            checkAnswer(true);
            updateQuestion();
        });
        binding.buttonFalse.setOnClickListener(v -> {
            checkAnswer(false);
            updateQuestion();
        });

    }

    private void checkAnswer(boolean userAnswer) {
        boolean oriAnswer = questionList.get(currentQuestionIndex).isAnswerTrue();
        int snackMessageId = 0;

        if (userAnswer == oriAnswer){
            snackMessageId = R.string.correct_answer;
        }else{
            snackMessageId = R.string.incorrect_answer;
            shakeAnimation();
        }
        Snackbar.make(binding.cardView, snackMessageId, Snackbar.LENGTH_SHORT)
                .show();
    }

    private void updateQuestion() {
        String question = questionList.get(currentQuestionIndex).getAnswer();
        binding.quePanel.setText(question);
        updateCounter((ArrayList<Question>) questionList);
    }

    private void updateCounter(ArrayList<Question> questionArrayList) {
        binding.queNoPanel.setText(String.format(getString(R.string.text_formatted),
                currentQuestionIndex + 1, questionArrayList.size()));
    }

    private void shakeAnimation(){
        Animation shake = AnimationUtils.loadAnimation(MainActivity.this,
                R.anim.shake_animation);
        binding.cardView.setAnimation(shake);
    }
}