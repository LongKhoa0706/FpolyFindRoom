package com.longkhoa.fpolyfindroom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.model.Banner;


import java.util.ArrayList;
import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.ViewHolder> {
    private List<Banner> bannerList;
    private Context context;


    public BannerAdapter(ArrayList<Banner> bannerList , Context context){
        this.context = context;
        this.bannerList = bannerList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.cardview_banner,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Banner banner = bannerList.get(position);
        holder.image_banner.setImageResource(banner.getImage_banner());
    }

    @Override
    public int getItemCount() {
        return bannerList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_banner;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_banner = itemView.findViewById(R.id.image_banner);

        }
    }
}

