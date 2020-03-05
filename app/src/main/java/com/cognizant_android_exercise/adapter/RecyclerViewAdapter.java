package com.cognizant_android_exercise.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cognizant_android_exercise.R;
import com.cognizant_android_exercise.model.NewsItem;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.NewsViewHolder> {
    List<NewsItem> newsItems;
    Context context;

    public RecyclerViewAdapter(List<NewsItem> newsItems, Context context) {
        this.newsItems = newsItems;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout, parent, false);
        NewsViewHolder newsViewHolder = new NewsViewHolder(view);
        return newsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.newsItemTitle.setText(newsItems.get(position).getTitle());
        holder.newsItemDescription.setText(newsItems.get(position).getDescription());

        String imageUrlString = newsItems.get(position).getImageUrl();
        String imageUrl = null;
        if (imageUrlString != null) {
            imageUrl = imageUrlString.replace("http", "https");
        }

        Picasso
                .with(context)
                .load(imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher_round)
                .fit()
                .into(holder.newsItemImageView);
//        Picasso.with(context).load(imageUrlString).into(holder.newsItemImageView);

    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView newsItemTitle;
        TextView newsItemDescription;
        ImageView newsItemImageView;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            newsItemTitle = itemView.findViewById(R.id.news_item_title);
            newsItemDescription = itemView.findViewById(R.id.news_item_description);
            newsItemImageView = itemView.findViewById(R.id.news_item_image);
        }
    }
}
