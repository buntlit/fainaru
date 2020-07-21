package com.example.fainaruappu.presenter;

import android.util.Log;

import com.example.fainaruappu.model.Model;
import com.example.fainaruappu.view.IViewHolder;

import java.util.List;

import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class MainPresenter extends MvpPresenter<IViewHolder> {

    private Model model = new Model();
    private static final String TAG = "MAIN PRESENTER";

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    private List<Integer> list = model.getCount();

    RecyclerPresenter recyclerPresenter = new RecyclerPresenter();

    private class RecyclerPresenter implements IRecyclerPresenter {


        @Override
        public List<Integer> bindView(IViewHolder iViewHolder, int position) {
            list.set(position, list.get(position) + 1);
            Log.d(TAG, "Count " + list.get(position));
            iViewHolder.setText(String.valueOf(list.get(position)));
            model.setCount(list);
            getViewState().setText(String.valueOf(list.get(position)));
            return list;
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    public RecyclerPresenter getRecyclerPresenter() {
        return recyclerPresenter;
    }

    public List<Integer> getList() {
        return list;
    }
}