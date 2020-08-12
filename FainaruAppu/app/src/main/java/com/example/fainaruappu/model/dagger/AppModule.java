package com.example.fainaruappu.model.dagger;

import android.app.Application;

import com.example.fainaruappu.model.Model;
import com.example.fainaruappu.model.api.ApiHelper;
import com.example.fainaruappu.model.database.Data;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    Model provideModel() {
        return new Model();
    }

    @Singleton
    @Provides
    ApiHelper provideApiHelper(){
        return new ApiHelper();
    }

    @Singleton
    @Provides
    Data provideData(){
        return new Data();
    }

}
