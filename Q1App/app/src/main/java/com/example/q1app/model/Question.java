package com.example.q1app.model;

public class Question {
    private int answerResId;
    private boolean answerTrue;
    private boolean userAnswer;

    public Question(int answerResId, boolean answerTrue) {
        this.answerResId = answerResId;
        this.answerTrue = answerTrue;
    }

    public int getAnswerResId() {
        return answerResId;
    }
    public boolean isAnswer() {
        return answerTrue;
    }
    public void setAnswerResId(int answerResId) {
        this.answerResId = answerResId;
    }
    public void setAnswerTrue(boolean answerTrue) {
        this.answerTrue = answerTrue;
    }

    public boolean isuserAnswer() {
        return userAnswer;
    }
    public void setuserAnswer(boolean userAnswer) {
        this.userAnswer = userAnswer;
    }


}
