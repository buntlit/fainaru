package com.example.fainaruappu;

import com.example.fainaruappu.model.api.ApiHelper;
import com.example.fainaruappu.model.database.AppDatabase;
import com.example.fainaruappu.model.database.Data;
import com.example.fainaruappu.model.database.DataDao;
import com.example.fainaruappu.model.entity.Hit;
import com.example.fainaruappu.model.entity.Photo;
import com.example.fainaruappu.presenter.MainPresenter;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

@RunWith(MockitoJUnitRunner.class)
public class PresenterRetrofitTest {

    private MainPresenter presenter;

    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(__ -> Schedulers.trampoline());
    }

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getImage_isCorrect() {

        ApiHelper apiHelper = Mockito.mock(ApiHelper.class);
        Photo photo = new Photo();
        photo.hits = new ArrayList<>();
        Hit hit = new Hit();
        hit.webformatURL = "https://pixabay.com";
        photo.hits.add(hit);
        Mockito.when(apiHelper.requestServer()).thenReturn(Observable.just(photo));

    }

    @Test
    public void getData_isCorrect(){
        AppDatabase appDatabase = Mockito.mock(AppDatabase.class);
        DataDao datasDao = Mockito.mock(DataDao.class);
        Data data = new Data();
        data.id = 1;
        data.clicks = 0;
        data.url = "https://pixabay.com";
        List<Data> list = new ArrayList<>();
        list.add(data);
        Mockito.when(appDatabase.dataDao()).thenReturn(datasDao);
        Mockito.when(datasDao.getList()).thenReturn(list);
    }
}
