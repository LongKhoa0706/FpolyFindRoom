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
import com.longkhoa.fpolyfindroom.model.Room;


import java.util.ArrayList;
import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {
    private List<Room> roomList;
    private Context context;


    public RoomAdapter(ArrayList<Room> roomList , Context context){
        this.context = context;
        this.roomList = roomList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.cardview_room,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Room room = roomList.get(position);
        holder.image_room.setImageResource(room.getImage_room());
        holder.image_favorite.setImageResource(room.getImage_favorite());
        holder.image_room1.setImageResource(room.getImage_room1());
        holder.txt_loai.setText(room.getTxt_loai());
        holder.txt_title.setText(room.getTxt_title());
        holder.txt_price.setText(room.getTxt_price());
        holder.txt_title1.setText(room.getTxt_title1());
        holder.txt_loai1.setText(room.getTxt_loai1());
        holder.txt_price1.setText(room.getTxt_price1());
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_room, image_favorite,image_room1;
        TextView txt_loai, txt_title, txt_price, txt_loai1,txt_title1,txt_price1;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_room = itemView.findViewById(R.id.image_room);
            image_favorite=itemView.findViewById(R.id.favorite);
            image_room1=itemView.findViewById(R.id.image_room1);
            txt_loai=itemView.findViewById(R.id.txt_loai);
            txt_title=itemView.findViewById(R.id.txt_title);
            txt_price=itemView.findViewById(R.id.txt_price);
            txt_loai1=itemView.findViewById(R.id.txt_loai1);
            txt_title1=itemView.findViewById(R.id.txt_title1);
            txt_price1=itemView.findViewById(R.id.txt_price1);
        }
    }
}

