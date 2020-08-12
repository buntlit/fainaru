package com.example.fainaruappu.model.dagger;



import com.example.fainaruappu.presenter.MainPresenter;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component (modules = AppModule.class)
public interface AppComponent {

    void inject (MainPresenter mainPresenter);

}
