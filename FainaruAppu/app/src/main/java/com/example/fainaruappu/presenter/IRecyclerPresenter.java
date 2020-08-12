package com.example.fainaruappu.presenter;
import com.example.fainaruappu.view.IViewHolder;

import java.util.List;

public interface IRecyclerPresenter {

    List<Integer> updateText(IViewHolder iViewHolder, int position);

    List<Integer> bindViewText(IViewHolder iViewHolder, int position);

    String bindViewImage(IViewHolder iViewHolder, int position, int count);

    int getItemCount();

}
