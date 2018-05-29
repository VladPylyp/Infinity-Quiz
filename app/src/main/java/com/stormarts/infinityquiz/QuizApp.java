package com.stormarts.infinityquiz;

import android.app.Application;

import com.stormarts.infinityquiz.Dagger.ApplicationComponent;
import com.stormarts.infinityquiz.Dagger.ApplicationModule;
import com.stormarts.infinityquiz.Dagger.DaggerApplicationComponent;
import com.stormarts.infinityquiz.Dagger.FirebaseCloudDatabaseModule;


public class QuizApp extends Application  {

    private ApplicationComponent applicationComponent;
    private static QuizApp application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(application))
                .firebaseCloudDatabaseModule(new FirebaseCloudDatabaseModule())
                .build();
    }

    public static QuizApp getApplication(){
        return application;
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
