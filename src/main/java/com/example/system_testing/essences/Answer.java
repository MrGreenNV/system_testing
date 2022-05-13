package com.example.system_testing.essences;

public class Answer {
    private String nameAnswer;
    boolean isTrueAnswer = false;

    public Answer() {
    }

    public Answer(String nameAnswer, boolean isTrueAnswer) {
        this.nameAnswer = nameAnswer;
        this.isTrueAnswer = isTrueAnswer;
    }

    public String getNameAnswer() {
        return nameAnswer;
    }

    public void setNameAnswer(String nameAnswer) {
        this.nameAnswer = nameAnswer;
    }

    public boolean isTrueAnswer() {
        return isTrueAnswer;
    }

    public void setTrueAnswer(boolean trueAnswer) {
        isTrueAnswer = trueAnswer;
    }
}
