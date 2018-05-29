package com.stormarts.infinityquiz.ui.activity;


import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.auth.AuthUI;
import com.stormarts.infinityquiz.model.Question;
import com.stormarts.infinityquiz.presentation.view.StartView;

import java.util.Arrays;
import java.util.List;

public class BaseStartActivity extends BaseActivity implements StartView {

    private static final String LOGTAG = BaseStartActivity.class.getCanonicalName() ;
    List<AuthUI.IdpConfig> signInProviders = Arrays.asList(
            new AuthUI.IdpConfig.EmailBuilder().build(),
            new AuthUI.IdpConfig.GoogleBuilder().build());

    static final int PG_SIGN_IN = 2563;
    static final int FB_SIGN_IN = 2564;



    void signIn(){
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(signInProviders)
                        .build(), FB_SIGN_IN);

    }

    void logOut(){

    }

    void startStartActivity(){
        Intent intent = StartActivity.getIntent(this);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public void setCurrentQuestion(Question question) {

    }

    @Override
    public void onSignIn() {
        signIn();
    }

    @Override
    public void onRegisterNewUser() {

    }

    @Override
    public void onUserReady() {

    }

    @Override
    public void showCoins(int coins) {

    }

    @Override
    public void onLogOut() {
        logOut();
    }

}
