package com.longkhoa.fpolyfindroom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.longkhoa.fpolyfindroom.R;

import java.util.List;

public class SlideImageAdapter extends RecyclerView.Adapter<SlideImageAdapter.ViewHoler> {
    private Context context;
    private List<String> listImage;
    private int layout;

    public SlideImageAdapter(Context context, List<String> listImage, int layout) {
        this.context = context;
        this.listImage = listImage;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layout, null, false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        Glide.with(context).load(listImage.get(position)).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return listImage.size();
    }


    public class ViewHoler extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageCustomItemImage);
        }
    }
}
