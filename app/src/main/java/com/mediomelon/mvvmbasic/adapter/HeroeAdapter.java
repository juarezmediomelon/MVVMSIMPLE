package com.mediomelon.mvvmbasic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mediomelon.mvvmbasic.R;
import com.mediomelon.mvvmbasic.model.Hero;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HeroeAdapter extends RecyclerView.Adapter<HeroeAdapter.HeroViewHolder> {

    Context mCtx;
    List<Hero> heroList;

    public HeroeAdapter(Context mCtx, List<Hero> heroList) {
        this.mCtx = mCtx;
        this.heroList = heroList;
    }

    @NonNull
    @Override
    public HeroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.cardview_heroe, parent, false);
        return new HeroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroViewHolder holder, int position) {
        Hero hero = heroList.get(position);

        Picasso.get().load(hero.getImageurl()).into(holder.imageView);
        holder.textView.setText(hero.getName());
    }

    @Override
    public int getItemCount() {
        return heroList.size();
    }

    class HeroViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public HeroViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
