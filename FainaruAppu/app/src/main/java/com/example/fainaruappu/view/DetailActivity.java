package com.example.fainaruappu.view;

import android.os.Bundle;

import com.example.fainaruappu.R;
import com.example.fainaruappu.presenter.DetailPresenter;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

import static com.example.fainaruappu.view.MainActivity.EXTRA_POS;

public class DetailActivity extends MvpAppCompatActivity implements DetailView {
    int itemPosition;

    @InjectPresenter
    DetailPresenter detailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemPosition = (int) getIntent().getExtras().get(EXTRA_POS) + 1;
        setContentView(R.layout.activity_detail);
        detailPresenter.logging(itemPosition);
    }
}
