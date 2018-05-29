package com.stormarts.infinityquiz.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.arellomobile.mvp.presenter.PresenterType;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.stormarts.infinityquiz.R;
import com.stormarts.infinityquiz.presentation.presenter.StartPresenter;

import com.arellomobile.mvp.presenter.InjectPresenter;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartActivity extends BaseStartActivity {
    public static final String TAG = "StartActivity";

    private static final int START_DELAY = 500;

    private final Handler mStartHandler = new Handler();

    @BindView(R.id.fullscreen_content)
    View mContentView;

    private final Runnable mStartRunnable = () -> signIn();


    @InjectPresenter
    StartPresenter mStartPresenter;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, StartActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);

        mBaseContentView = mContentView;

    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        mStartHandler.postDelayed(mStartRunnable, START_DELAY);
    }

    @OnClick(R.id.fullscreen_content)
    void onContentClick(View v){
        toggle();
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FB_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK) {
                // Successfully signed in

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user != null){
                    Log.d(TAG, "onActivityResult User Uuid: " + user.getUid());
                    mStartPresenter.getUser();
                    // тут я отримую айді яий треба передати в презентер !!!
                } else{
                    // user is not signedIn;
                }
                // ...
            } else {
                // Sign in failed, check response for error code
                // ...
            }
        }
    }


    private void createNickNameDialog(){

        AlertDialog dialog;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View rootView = inflater.inflate(R.layout.nickname_dialog, null);
        builder.setView(rootView);
        EditText nicknameET = rootView.findViewById(R.id.nicknameEditText);
        dialog = builder.create();
        rootView.findViewById(R.id.ok_button).setOnClickListener(v -> {
            if(!"".equals(nicknameET.getText().toString())){
                mStartPresenter.registerNewUser(nicknameET.getText().toString());
                dialog.dismiss();
            } else{
                Toast.makeText(this, R.string.enter_your_nickname, Toast.LENGTH_SHORT).show();
            }

        });
        rootView.findViewById(R.id.cancel_button).setOnClickListener(v -> {
            dialog.dismiss();
            finish();
        });
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void onRegisterNewUser() {
       createNickNameDialog();

    }

    @Override
    public void onUserReady() {
        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = MenuActivity.getIntent(this);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
        } else{
            onSignIn();
        }

    }

    @Override
    protected void onDestroy() {
        mStartPresenter.destroyComponents();
        super.onDestroy();
    }
}
