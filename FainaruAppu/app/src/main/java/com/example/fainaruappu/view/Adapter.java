package com.example.fainaruappu.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fainaruappu.R;
import com.example.fainaruappu.model.GlideLoader;
import com.example.fainaruappu.presenter.IRecyclerPresenter;
import com.example.fainaruappu.presenter.MainPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private IRecyclerPresenter iRecyclerPresenter;
    private static MainPresenter presenter = new MainPresenter();
    private static List<Integer> list = presenter.getListCount();
    private GlideLoader glideLoader;

    public Adapter(Context context, IRecyclerPresenter iRecyclerPresenter) {
        this.iRecyclerPresenter = iRecyclerPresenter;
        this.glideLoader = new GlideLoader(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Adapter.MyViewHolder holder, final int position) {

        iRecyclerPresenter.bindViewImage(holder, position);
        holder.setText(String.valueOf(list.get(position)));
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list = iRecyclerPresenter.bindViewText(holder, position);
                String url = iRecyclerPresenter.bindViewImage(holder, position);

                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra(MainActivity.EXTRA_POS, position);
                intent.putExtra(MainActivity.EXTRA_URL, url);
                view.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (list.size() < iRecyclerPresenter.getItemCount()) {
            for (int i = list.size(); i < iRecyclerPresenter.getItemCount(); i++) {
                list.add(0);
            }
        } else if (list.size() > iRecyclerPresenter.getItemCount()){
            for (int i = list.size(); i > iRecyclerPresenter.getItemCount(); i--) {
                list.remove(i-1);
            }
        }
        return iRecyclerPresenter.getItemCount();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements IViewHolder {

        @BindView(R.id.image_view_item)
        ImageView imageView;

        @BindView(R.id.count)
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setText(String count) {
            textView.setText(count);
        }

        @Override
        public void setImage(String url) {
            glideLoader.loadImage(url, imageView);
        }

        @Override
        public void updateRecyclerView() {

        }
    }
}