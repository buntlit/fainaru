package com.example.fainaruappu.presenter;

import android.util.Log;

import com.example.fainaruappu.model.Model;
import com.example.fainaruappu.view.IViewHolder;

import java.util.List;

public class MainPresenter {

    private Model model = new Model();
    private List<Integer> list = model.getCount();

    RecyclerPresenter recyclerPresenter = new RecyclerPresenter();

    private class RecyclerPresenter implements IRecyclerPresenter {


        @Override
        public void bindView(IViewHolder iViewHolder) {
            iViewHolder.setText(String.valueOf(list.get(iViewHolder.getPos())));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    public void onItemClick(int position) {
        list.set(position, list.get(position) + 1);
        Log.d("COUNT", String.valueOf(list.get(position)));

    }

    public RecyclerPresenter getRecyclerPresenter() {
        return recyclerPresenter;
    }
}