package com.longkhoa.fpolyfindroom.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.model.Room;
import com.longkhoa.fpolyfindroom.view.CallbackRoomAdapter;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {
    private List<Room> arrRoom;
    private Context context;
    private int layout;
    private CallbackRoomAdapter callbackRoomAdapter;

    public RoomAdapter(List<Room> arrRoom, Context context, int layout,CallbackRoomAdapter callbackRoomAdapter) {
        this.arrRoom = arrRoom;
        this.context = context;
        this.layout = layout;
        this.callbackRoomAdapter = callbackRoomAdapter;

    }


    @NonNull
    @Override
    public RoomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final  Room room = arrRoom.get(position);
        holder.txtTitleRoom.setText(room.getTitle());
        holder.txtAddress.setText(room.getAddress());
        holder.txtAddressRoom.setText(room.getAddress());
//        holder.txtPriceRoom.setText(room.getPrice());
        holder.txtCategoriesRoom.setText(room.getCategoriesRoom());
        holder.cardViewRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callbackRoomAdapter.onClickListenerCardView(room);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrRoom.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgRoom;
        TextView txtPriceRoom,txtCategoriesRoom,txtTitleRoom,txtAddressRoom,txtAddress;
        MaterialCardView cardViewRoom;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgRoom = itemView.findViewById(R.id.imgItemRoom);
            txtPriceRoom = itemView.findViewWithTag(R.id.txtItemPriceRoom);
            txtCategoriesRoom = itemView.findViewById(R.id.txtItemCategoriRoom);
            txtTitleRoom = itemView.findViewById(R.id.txtItemTitleRoom);
            txtAddressRoom = itemView.findViewById(R.id.txtItemAddressRoom);
            txtAddress = itemView.findViewById(R.id.txtItemAddress);
            cardViewRoom = itemView.findViewById(R.id.cardViewRoom);

        }
    }
}
