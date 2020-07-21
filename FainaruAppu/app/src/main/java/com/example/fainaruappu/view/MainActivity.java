package com.example.fainaruappu.view;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.fainaruappu.R;
import com.example.fainaruappu.presenter.MainPresenter;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class MainActivity extends MvpAppCompatActivity implements IViewHolder {

    @InjectPresenter
    MainPresenter presenter;

    static final String EXTRA_POS = "item_position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
    }



    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        Adapter adapter = new Adapter(presenter.getRecyclerPresenter());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setText(String text) {

    }
}