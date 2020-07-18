package com.example.fainaruappu.view;

import android.util.Log;
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

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private IRecyclerPresenter iRecyclerPresenter;
    private MainPresenter presenter = new MainPresenter();

    public Adapter(IRecyclerPresenter iRecyclerPresenter) {
        this.iRecyclerPresenter = iRecyclerPresenter;
    }


    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Adapter.MyViewHolder holder, final int position) {
        holder.position = position;
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onItemClick(position);
                Log.d("POSITION", String.valueOf(position));
                iRecyclerPresenter.bindView(holder);
            }
        });

    }

    @Override
    public int getItemCount() {
        return iRecyclerPresenter.getItemCount();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements IViewHolder {

        private ImageView imageView;
        private TextView textView;
        private int position;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.count);
        }

        @Override
        public void setText(String count) {
            textView.setText(count);
        }

        @Override
        public int getPos() {
            return position;
        }
    }
}
