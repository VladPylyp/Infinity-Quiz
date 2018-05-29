package com.stormarts.infinityquiz.presentation.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

public interface GameView extends MvpView {

    void setCurrentQuestion(String currentQuestion);
    void setCurrentAnswers(List<String> answersList);
    void setScore(int score);
    void setCoins(int coins);
    void showNewLevelAskDialog();
    void setCurrentQuestionNumber(int currentQuestionNumber);
    void setQuestionsCount(int questionsCount);

}
