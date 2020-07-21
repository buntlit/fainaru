package com.example.fainaruappu.view;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fainaruappu.R;
import com.example.fainaruappu.presenter.IRecyclerPresenter;
import com.example.fainaruappu.presenter.MainPresenter;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private IRecyclerPresenter iRecyclerPresenter;
    private static MainPresenter presenter = new MainPresenter();
    private static List<Integer> list = presenter.getList();

    public Adapter(IRecyclerPresenter iRecyclerPresenter) {
        this.iRecyclerPresenter = iRecyclerPresenter;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Adapter.MyViewHolder holder, final int position) {

        holder.setText(String.valueOf(list.get(position)));
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list = iRecyclerPresenter.bindView(holder, position);
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra(MainActivity.EXTRA_POS, position);
                view.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return iRecyclerPresenter.getItemCount();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements IViewHolder {

        private ImageView imageView;
        private TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.count);
        }

        @Override
        public void setText(String count) {
            textView.setText(count);
        }
    }
}