package com.longkhoa.fpolyfindroom.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.model.MyStatusRoom;
import com.longkhoa.fpolyfindroom.model.Room;
import com.longkhoa.fpolyfindroom.view.room.CallbackRoomAdapter;

import java.util.List;

public class HomeInAdapter extends RecyclerView.Adapter<HomeInAdapter.ViewHolder> {
    private Context context;
    private List<Room> arrListRoom;
    private int layout;
    private CallbackRoomAdapter callbackRoomAdapter;

    public HomeInAdapter(Context context, List<Room> arrListRoom, int layout, CallbackRoomAdapter callbackRoomAdapter) {
        this.context = context;
        this.arrListRoom = arrListRoom;
        this.layout = layout;
        this.callbackRoomAdapter = callbackRoomAdapter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Room room = arrListRoom.get(position);
        holder.txtItemPriceRoom.setText(String.valueOf(room.getPrice()+"đ"));
        holder.txtItemCategoriRoom.setText(room.getType());
        holder.txtItemTitleRoom.setText(room.getTitle());
        holder.txtItemAddressRoom.setText(room.getLocation());

//
//        holder.materialCardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                callbackRoomAdapter.onClickListenerCardView(room);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return arrListRoom.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgItemRoom,imgdiachi;
        TextView txtItemPriceRoom,txtItemCategoriRoom,txtItemTitleRoom,txtItemAddressRoom,txtItemAddress;
        MaterialCardView materialCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgdiachi=itemView.findViewById(R.id.imgdiachi);
//            imgItemRoom=itemView.findViewById(R.id.imgItemRoom);
            txtItemPriceRoom=itemView.findViewById(R.id.txtItemPriceRoom);
            txtItemCategoriRoom=itemView.findViewById(R.id.txtItemCategoriRoom);
            txtItemTitleRoom=itemView.findViewById(R.id.txtItemTitleRoom);
            txtItemAddressRoom=itemView.findViewById(R.id.txtItemAddressRoom);
            txtItemAddress=itemView.findViewById(R.id.txtItemAddress);
            materialCardView = itemView.findViewById(R.id.cardViewItem);
        }
    }
}
