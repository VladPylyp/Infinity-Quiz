package com.stormarts.infinityquiz.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Question {

    @SerializedName("answers_list")
    @Expose
    private List<String> answersList = null;
    @SerializedName("correct_answer")
    @Expose
    private String correctAnswer;
    @SerializedName("question")
    @Expose
    private String question;

    public Question() {
    }

    public Question(String question, List<String> answersList, String correctAnswer) {
        this.question = question;
        this.answersList = answersList;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;

    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswersList() {
        return answersList;
    }

    public void setAnswersList(List<String> answersList) {
        this.answersList = answersList;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
