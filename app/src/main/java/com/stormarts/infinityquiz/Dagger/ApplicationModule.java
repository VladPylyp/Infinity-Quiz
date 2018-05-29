package com.stormarts.infinityquiz.Dagger;

import android.content.Context;
import android.content.res.AssetManager;
import android.preference.PreferenceManager;

import com.stormarts.infinityquiz.AndroidResourceManager;
import com.stormarts.infinityquiz.QuizApp;
import com.stormarts.infinityquiz.ResourceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final QuizApp application;


    public ApplicationModule(QuizApp application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext(){
        return application;
    }

    @Provides
    @Singleton
    AssetManager provideAssets(){
        return application.getAssets();
    }

    @Provides
    @Singleton
    ResourceManager provideResourceManager(AssetManager assets){
        return new AndroidResourceManager(assets);
    }

}
