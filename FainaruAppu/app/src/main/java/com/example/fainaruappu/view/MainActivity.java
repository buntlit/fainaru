package com.example.fainaruappu.view;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.fainaruappu.R;
import com.example.fainaruappu.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class MainActivity extends MvpAppCompatActivity implements IViewHolder {

    private Adapter adapter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @InjectPresenter
    MainPresenter presenter;

    @ProvidePresenter
    public MainPresenter providePresenter() {
        return new MainPresenter();
    }

    static final String EXTRA_POS = "item_position";
    static final String EXTRA_URL = "item_url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initRecyclerView();

    }


    private void initRecyclerView() {

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(this, presenter.getRecyclerPresenter());
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void setText(String text) {

    }

    @Override
    public void setImage(String url) {

    }

    @Override
    public void updateRecyclerView() {

        adapter.notifyDataSetChanged();

    }
}