package com.example.fainaruappu.view;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndStrategy;
import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndStrategy.class)
public interface IViewHolder extends MvpView {

    void setText(String text);

    void setImage(String url);

    @StateStrategyType(value = SkipStrategy.class)
    void updateRecyclerView();
}
