package com.stormarts.infinityquiz.ui.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.stormarts.infinityquiz.R;
import com.stormarts.infinityquiz.presentation.view.MenuView;
import com.stormarts.infinityquiz.presentation.presenter.MenuPresenter;



import com.arellomobile.mvp.presenter.InjectPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuActivity extends BaseStartActivity implements MenuView {
    private static final String LOGTAG = "MenuActivity";



    @BindView(R.id.menu_content)
    View mContentView;


    @InjectPresenter
    MenuPresenter mMenuPresenter;


    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, MenuActivity.class);

        return intent;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);

        mBaseContentView = mContentView;

    }

    @OnClick(R.id.menu_content)
    void onContentClick(View v){
        toggle();
    }


    @Override
    public void showCoins(int coins) {

    }


    @OnClick(R.id.logout_button)
    void logOutButtonClick(View v){
        mMenuPresenter.logOut();
    }


    @Override
    public void onLoghOut() {
        logOut();
    }

    @Override
    void logOut(){
        try {
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(task -> {
                        startStartActivity();
                        finish();
                    });
        }catch (Exception e){
            Log.e(LOGTAG, "logOut: " + e.getMessage());
        }

    }


    @OnClick(R.id.start_game_button)
    void onStartClick(){
        Intent intent = SelectLevelActivity.getIntent(this);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
