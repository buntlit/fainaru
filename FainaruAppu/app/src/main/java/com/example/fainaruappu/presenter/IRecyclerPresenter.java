package com.example.fainaruappu.presenter;


import com.example.fainaruappu.view.IViewHolder;

public interface IRecyclerPresenter {
    void bindView(IViewHolder iViewHolder);
    int getItemCount();
}
