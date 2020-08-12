package com.example.fainaruappu.presenter;

import android.util.Log;

import com.example.fainaruappu.model.Model;
import com.example.fainaruappu.model.api.ApiHelper;
import com.example.fainaruappu.model.App;
import com.example.fainaruappu.model.database.Data;
import com.example.fainaruappu.model.database.DataDao;
import com.example.fainaruappu.model.entity.Hit;
import com.example.fainaruappu.model.entity.Photo;
import com.example.fainaruappu.view.IViewHolder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class MainPresenter extends MvpPresenter<IViewHolder> {

    @Inject
    Model model;

    @Inject
    ApiHelper apiHelper;

    @Inject
    Data data;

    private List<Integer> listCount = new ArrayList<>();
    private List<Hit> hitList;
    private DataDao dataDao;
    private static final String TAG = "MAIN PRESENTER";

    @Override
    protected void onFirstViewAttach() {

        getData();
        getAllPhoto();

    }

    public MainPresenter() {

        dataDao = App.getAppDatabase().dataDao();
        App.getAppComponent().inject(this);

    }

    public void getAllPhoto() {

        Observable<Photo> single = apiHelper.requestServer();

        Disposable disposable = single.observeOn(AndroidSchedulers.mainThread()).subscribe(photos -> {

            hitList = photos.hits;
            getViewState().updateRecyclerView();

            if (model.getUrl().size() < hitList.size()) {
                for (int i = 0; i < hitList.size(); i++) {
                    model.getUrl().add(hitList.get(i).webformatURL);
                    putData(hitList.get(i).webformatURL, 0);
                }
            } else {
                for (int i = 0; i < hitList.size(); i++) {
                    if (!(model.getUrl().get(i).equals(hitList.get(i).webformatURL))) {
                        model.getUrl().set(i, hitList.get(i).webformatURL);
                        updateData(i+1, hitList.get(i).webformatURL, model.getCount().get(i));
                    }
                }
            }

        }, throwable -> {
            Log.e(TAG, "onError " + throwable);
        });
    }

    public void putData(String url, int clicks) {

        data.url = url;
        data.clicks = clicks;

        Disposable disposable = dataDao.insert(data).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(id -> {
        }, throwable -> {
            Log.e(TAG, "putData: " + throwable);
        });
    }

    public void getData() {

        Disposable disposable = dataDao.getAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(datas -> {

            List<String> url = new ArrayList<>();
            List<Integer> clicks = new ArrayList<>();
            Data data;

            for (int i = 0; i < datas.size(); i++) {
                data = datas.get(i);
                url.add(data.url);
                clicks.add(data.clicks);
            }

            model.setCount(clicks);
            model.setUrl(url);
            listCount = clicks;

        }, throwable -> {
            Log.e(TAG, "getData: " + throwable);
        });
    }

    public void updateData(int position, String url, int clicks) {

        data.id = position;
        data.url = url;
        data.clicks = clicks;

        Disposable disposable = dataDao.update(data).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(id -> {
        }, throwable -> {
            Log.e(TAG, "updateData: " + throwable);
        });
    }

    RecyclerPresenter recyclerPresenter = new RecyclerPresenter();

    private class RecyclerPresenter implements IRecyclerPresenter {

        @Override
        public List<Integer> bindViewText(IViewHolder iViewHolder, int position) {

            iViewHolder.setText(String.valueOf(listCount.get(position)));
            getViewState().setText(String.valueOf(listCount.get(position)));
            return listCount;

        }

        @Override
        public List<Integer> updateText(IViewHolder iViewHolder, int position) {

            listCount.set(position, listCount.get(position) + 1);
            Log.d(TAG, "Count " + listCount.get(position));
            iViewHolder.setText(String.valueOf(listCount.get(position)));
            getViewState().setText(String.valueOf(listCount.get(position)));
            updateData(position + 1, model.getUrl().get(position), listCount.get(position));
            return listCount;

        }

        @Override
        public String bindViewImage(IViewHolder iViewHolder, int position, int count) {

            iViewHolder.setImage(model.getUrl().get(position));
            return model.getUrl().get(position);

        }

        @Override
        public int getItemCount() {

            if (hitList != null) {
                if (listCount.size() == 0) {
                    for (int i = 0; i < hitList.size(); i++) {
                        listCount.add(0);
                    }
                }
                return hitList.size();
            } else return 0;
        }

    }


    public RecyclerPresenter getRecyclerPresenter() {
        return recyclerPresenter;
    }
}