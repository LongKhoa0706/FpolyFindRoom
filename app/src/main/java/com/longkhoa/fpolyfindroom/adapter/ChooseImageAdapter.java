package com.longkhoa.fpolyfindroom.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.longkhoa.fpolyfindroom.R;

import java.util.List;

public class ChooseImageAdapter extends RecyclerView.Adapter<ChooseImageAdapter.ViewHolder> {
    private List<Uri> listImageUri;
    private Context context;
    private int layout;


    public ChooseImageAdapter(List<Uri> listImageUri, Context context, int layout) {
        this.listImageUri = listImageUri;
        this.context = context;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layout, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.imageView.setImageURI(listImageUri.get(position));
    }


    @Override
    public int getItemCount() {
        return listImageUri.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageCustomItemImage);
        }
    }
}
