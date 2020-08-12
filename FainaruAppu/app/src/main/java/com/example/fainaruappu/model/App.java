package com.example.fainaruappu.model;

import android.app.Application;

import androidx.room.Room;

import com.example.fainaruappu.model.dagger.AppComponent;
import com.example.fainaruappu.model.dagger.AppModule;
import com.example.fainaruappu.model.dagger.DaggerAppComponent;
import com.example.fainaruappu.model.database.AppDatabase;
import com.squareup.leakcanary.LeakCanary;

public class App extends Application {

    private static AppDatabase appDatabase;
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = generateAppComponent();
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "room_database").build();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }

private AppComponent generateAppComponent(){
    return DaggerAppComponent
            .builder()
            .appModule(new AppModule(this))
            .build();
}
}
