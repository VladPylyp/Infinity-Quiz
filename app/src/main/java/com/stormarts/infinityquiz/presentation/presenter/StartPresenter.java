package com.stormarts.infinityquiz.presentation.presenter;


import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.stormarts.infinityquiz.QuizApp;
import com.stormarts.infinityquiz.data.CloudDatabase;
import com.stormarts.infinityquiz.data.CloudDatabaseError;
import com.stormarts.infinityquiz.data.DatabaseUserEventListener;
import com.stormarts.infinityquiz.data.FirebaseCloudDatabase;
import com.stormarts.infinityquiz.model.GameAuth;
import com.stormarts.infinityquiz.model.User;
import com.stormarts.infinityquiz.presentation.view.StartView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import static com.stormarts.infinityquiz.ui.activity.StartActivity.TAG;

@InjectViewState
public class StartPresenter extends MvpPresenter<StartView> {

    public static final String LOGTAG = "StartPresenter";
    FirebaseUser fUser;

    @Inject
    CloudDatabase firebaseCloudDatabase;

    public StartPresenter() {
        QuizApp.getApplication().getApplicationComponent().inject(this);
       // firebaseCloudDatabase = QuizApp.getApplication().getApplicationComponent().getDatabase();
        firebaseCloudDatabase.setUserDataListener(databaseUserEventListener);
    }



    public void getUser(){
        fUser = FirebaseAuth.getInstance().getCurrentUser();
        if (fUser != null) {
            firebaseCloudDatabase.getUser(fUser.getUid());
        }



        // тут я маю отримаити користуваа по айді з фаєрбейс бази даних, або створити нового якщо
        //якщо такого не існує.!

    }

    private DatabaseUserEventListener databaseUserEventListener = new DatabaseUserEventListener() {

        @Override
        public void onUserExist(User user) {
            GameAuth.getInstance().setCurrentUser(user);
            if (GameAuth.getInstance().getCurrentUser() != null) {
                Log.d(TAG, "onDataChange (get user statement) : User " +
                        GameAuth.getInstance()
                                .getCurrentUser()
                                .toString());

                getViewState().onUserReady();
            }
        }

        @Override
        public void onUserNotExist() {
            Log.d(TAG, "onUserNotExist:" );
            registerNewUser();
        }

        @Override
        public void onError(CloudDatabaseError error) {
            Log.d(LOGTAG, "loadPost:onCancelled" + error.getMessage());
            Log.d(LOGTAG, "loadPost:onCancelled Error code = " + error.getCode());
        }
    };

    private void registerNewUser(){
        getViewState().onRegisterNewUser();
    }

    public void registerNewUser(String nickname){
        GameAuth.getInstance().setNewUser(fUser.getUid(), nickname);
        if(fUser!= null) {
            Log.i(TAG, "registerNewUser: Uuid = " + fUser.getUid());
            firebaseCloudDatabase.setNewUser(GameAuth.getInstance().getCurrentUser());
        }
    }


    public void destroyComponents(){
        databaseUserEventListener = null;
        firebaseCloudDatabase = null;
    }

}
