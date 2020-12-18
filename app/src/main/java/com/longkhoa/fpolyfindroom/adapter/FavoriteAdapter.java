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
import com.longkhoa.fpolyfindroom.model.District;
import com.longkhoa.fpolyfindroom.model.Favorite;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private List<Favorite> favoriteList;
    private Context context;


    public FavoriteAdapter(ArrayList<Favorite> favoriteList , Context context){
        this.context = context;
        this.favoriteList = favoriteList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.cardview_favorite,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Favorite favorite = favoriteList.get(position);
        holder.image_favorite.setImageResource(favorite.getImage_favorite());
        holder.image_icon.setImageResource(favorite.getImage_icon());
        holder.txt_title.setText(favorite.getTxt_title());
        holder.txt_district.setText(favorite.getTxt_district());
        holder.txt_price.setText(favorite.getTxt_price());
    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_favorite, image_icon;
        TextView txt_title, txt_district, txt_price;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_favorite = itemView.findViewById(R.id.image_favorite);
//            image_icon =itemView.findViewById(R.id.image_icon);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_district=itemView.findViewById(R.id.txt_district);
            txt_price=itemView.findViewById(R.id.txt_price);
        }
    }
}

