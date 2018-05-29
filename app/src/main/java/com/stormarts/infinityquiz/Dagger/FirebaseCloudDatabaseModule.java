package com.stormarts.infinityquiz.Dagger;

import com.stormarts.infinityquiz.data.CloudDatabase;
import com.stormarts.infinityquiz.data.FirebaseCloudDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FirebaseCloudDatabaseModule {

    private CloudDatabase database;

    public FirebaseCloudDatabaseModule() {
        this.database = new FirebaseCloudDatabase();
    }

    @Singleton
    @Provides
    CloudDatabase provideDatabase(){
        return this.database;
    }

}
