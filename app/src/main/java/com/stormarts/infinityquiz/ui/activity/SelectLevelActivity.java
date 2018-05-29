package com.stormarts.infinityquiz.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.stormarts.infinityquiz.model.GameAuth;
import com.stormarts.infinityquiz.model.Level;
import com.stormarts.infinityquiz.presentation.view.SelectLevelView;
import com.stormarts.infinityquiz.presentation.presenter.SelectLevelPresenter;


import com.stormarts.infinityquiz.R;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectLevelActivity extends BaseActivity implements SelectLevelView {
    public static final String TAG = "SelectLevelActivity";


    @BindView(R.id.level_1_button)
    Button easyButton;
    @BindView(R.id.level_2_button)
    Button normalButton;
    @BindView(R.id.level_3_button)
    Button hardButton;
    @BindView(R.id.level_back_button)
    Button backButton;

    @BindView(R.id.select_level_content)
    View mContentView;


    @InjectPresenter
    SelectLevelPresenter mSelectLevelPresenter;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, SelectLevelActivity.class);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_level);
        ButterKnife.bind(this);
        mBaseContentView = mContentView;


        switch(GameAuth.getInstance().getCurrentUser().getAllowedLevel()){
            case Level.EASY:{
                easyButton.setEnabled(true);
                normalButton.setEnabled(false);
                normalButton.setTextColor(getResources().getColor(R.color.white_disabled));
                normalButton.setBackgroundTintList(ColorStateList.valueOf(getResources()
                        .getColor(R.color.black_overlay)));
                hardButton.setEnabled(false);
                hardButton.setTextColor(getResources().getColor(R.color.white_disabled));
                hardButton.setBackgroundTintList(ColorStateList.valueOf(getResources()
                        .getColor(R.color.black_overlay)));
                break;
            }
            case Level.MID:{
                easyButton.setEnabled(true);
                normalButton.setEnabled(true);
                hardButton.setEnabled(false);
                hardButton.setTextColor(getResources().getColor(R.color.white_disabled));
                hardButton.setBackgroundTintList(ColorStateList.valueOf(getResources()
                        .getColor(R.color.black_overlay)));
                break;

            }
            case Level.HARD:{
                easyButton.setEnabled(true);
                normalButton.setEnabled(true);
                hardButton.setEnabled(true);
                break;
            }
        }

    }

    @OnClick(R.id.select_level_content)
    void onContentClick(View v){
        toggle();
    }

    @OnClick(R.id.level_back_button)
    void onBackButtonClick(){
        onBackPressed();
    }

    private void startGameActivityWithLevel(int level){
        Intent gameIntent = GameActivity.getIntent(this);
        gameIntent.putExtra(Level.SELECTED, level);
        startActivity(gameIntent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @OnClick({R.id.level_1_button, R.id.level_2_button, R.id.level_3_button})
    void onLevelButtonClick(View v){
        Log.d(TAG, "onLevelButtonClick: " + v.getId());
        if(v.isEnabled()){
            switch (v.getId()){
                case R.id.level_1_button:{
                   startGameActivityWithLevel(Level.EASY);
                    break;
                }
                case R.id.level_2_button:{
                    startGameActivityWithLevel(Level.MID);
                    break;
                }
                case R.id.level_3_button:{
                    startGameActivityWithLevel(Level.HARD);
                    break;
                }
            }
        }


    }


}
