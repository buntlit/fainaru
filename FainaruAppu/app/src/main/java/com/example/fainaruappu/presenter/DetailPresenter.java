package com.example.fainaruappu.presenter;

import android.util.Log;

import com.example.fainaruappu.model.Model;
import com.example.fainaruappu.view.DetailView;

import moxy.MvpPresenter;

public class DetailPresenter extends MvpPresenter<DetailView> {

    private Model model = new Model();
    private static final String TAG = "DETAIL PRESENTER";

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    public void logging(int position) {
        Log.d(TAG, "Position " + position);
    }
}
