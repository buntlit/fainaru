package com.example.fainaruappu.presenter;

import android.util.Log;

import com.example.fainaruappu.model.Model;
import com.example.fainaruappu.model.api.ApiHelper;
import com.example.fainaruappu.model.entity.Hit;
import com.example.fainaruappu.model.entity.Photo;
import com.example.fainaruappu.view.IViewHolder;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class MainPresenter extends MvpPresenter<IViewHolder> {

    private Model model = new Model();
    private ApiHelper apiHelper = new ApiHelper();
    private List<Hit> hitList;
    private List<Integer> listCount = model.getCount();
    private static final String TAG = "MAIN PRESENTER";

    @Override
    protected void onFirstViewAttach() {
        getAllPhoto();
    }

    public void getAllPhoto() {
        Observable<Photo> single = apiHelper.requestServer();

        Disposable disposable = single.observeOn(AndroidSchedulers.mainThread()).subscribe(photos -> {
            hitList = photos.hits;
            getViewState().updateRecyclerView();

        }, throwable -> {
            Log.e(TAG, "onError " + throwable);
        });
    }


    RecyclerPresenter recyclerPresenter = new RecyclerPresenter();

    private class RecyclerPresenter implements IRecyclerPresenter {


        @Override
        public List<Integer> bindViewText(IViewHolder iViewHolder, int position) {
            listCount.set(position, listCount.get(position) + 1);
            Log.d(TAG, "Count " + listCount.get(position));
            iViewHolder.setText(String.valueOf(listCount.get(position)));
            model.setCount(listCount);
            getViewState().setText(String.valueOf(listCount.get(position)));
            return listCount;
        }

        @Override
        public String bindViewImage(IViewHolder iViewHolder, int position) {
            iViewHolder.setImage(hitList.get(position).webformatURL);
            return hitList.get(position).webformatURL;
        }

        @Override
        public int getItemCount() {
            if (hitList != null) {
                if (hitList.size() > listCount.size()) {
                    for (int i = listCount.size(); i < hitList.size(); i++) {
                        listCount.add(0);
                    }
                } else if (hitList.size() < listCount.size()) {
                    for (int i = listCount.size(); i > hitList.size(); i--) {
                        listCount.remove(i - 1);
                    }
                }
                model.setCount(listCount);
                return listCount.size();
            }
            return 0;
        }

    }

    public RecyclerPresenter getRecyclerPresenter() {
        return recyclerPresenter;
    }

    public List<Integer> getListCount() {
        return listCount;
    }
}