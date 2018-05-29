package com.stormarts.infinityquiz.Dagger;

import android.content.Context;
import android.content.res.AssetManager;

import com.stormarts.infinityquiz.data.CloudDatabase;
import com.stormarts.infinityquiz.presentation.presenter.GamePresenter;
import com.stormarts.infinityquiz.presentation.presenter.StartPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, FirebaseCloudDatabaseModule.class})
public interface ApplicationComponent {

    void inject(StartPresenter presenter);
    void inject(GamePresenter presenter);
    /*Context getContext();
    AssetManager getAssets();
    CloudDatabase getDatabase();*/

}
