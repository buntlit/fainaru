package com.example.fainaruappu.view;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.fainaruappu.R;
import com.example.fainaruappu.model.GlideLoader;
import com.example.fainaruappu.presenter.DetailPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

import static com.example.fainaruappu.view.MainActivity.EXTRA_POS;
import static com.example.fainaruappu.view.MainActivity.EXTRA_URL;

public class DetailActivity extends MvpAppCompatActivity implements DetailView {
    int itemPosition;
    String url;
    GlideLoader glideLoader;

    @BindView(R.id.image_view_detail)
    ImageView imageView;

    @InjectPresenter
    DetailPresenter detailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getExtra();

        ButterKnife.bind(this);
        glideLoader = new GlideLoader(this);
        detailPresenter.logging(itemPosition);
        glideLoader.loadImage(url, imageView);
    }

    public void getExtra(){
        itemPosition = (int) getIntent().getExtras().get(EXTRA_POS)+1;
        url = (String) getIntent().getExtras().get(EXTRA_URL);
    }
}
