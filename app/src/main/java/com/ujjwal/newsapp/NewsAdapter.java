package com.ujjwal.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    Context mContext;
    List<NewsDetail> mNewsDetailList;

    public NewsAdapter(Context mContext, List<NewsDetail> mNewsDetailList) {
        this.mContext = mContext;
        this.mNewsDetailList = mNewsDetailList;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mView = LayoutInflater.from(mContext).inflate(R.layout.place_holder,parent,false);
        return new NewsHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        NewsDetail newsDetail=mNewsDetailList.get(position);
        holder.setImageView(newsDetail.getImageUrl());
        holder.setTime(newsDetail.getTime());
        holder.setSource(newsDetail.getSource());
        holder.setTitle(newsDetail.getTitle());
        holder.setDescription(newsDetail.getDescription());
    }

    @Override
    public int getItemCount() {
        return mNewsDetailList.size();
    }

    public class NewsHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView time;
        TextView source;
        TextView title;
        TextView description;
        View view;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
        }

        public void setImageView(String url) {
           imageView=view.findViewById(R.id.image_holder);
           Glide.with(mContext).load(url).into(imageView);
        }

        public void setTime(String timing) {
           time=view.findViewById(R.id.timing);
           time.setText(timing);
        }

        public void setSource(String src) {
           source = view.findViewById(R.id.source);
           source.setText(src);
        }

        public void setTitle(String titl) {
           title=view.findViewById(R.id.title);
           title.setText(titl);
        }

        public void setDescription(String descrip) {
            description=view.findViewById(R.id.description);
            description.setText(descrip);
        }
    }
}
