package com.example.fainaruappu.presenter;
import com.example.fainaruappu.view.IViewHolder;

import java.util.List;

public interface IRecyclerPresenter {
    List<Integer> bindView(IViewHolder iViewHolder, int position);
    int getItemCount();
}
