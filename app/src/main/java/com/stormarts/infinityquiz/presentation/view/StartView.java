package com.stormarts.infinityquiz.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.stormarts.infinityquiz.model.Question;

public interface StartView extends MvpView {

    void setCurrentQuestion(Question question);
    void onSignIn();
    void onRegisterNewUser();
    void onUserReady();
    void showCoins(int coins);
    void onLogOut();

}
