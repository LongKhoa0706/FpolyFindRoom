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
import com.longkhoa.fpolyfindroom.model.Room;

import java.util.List;

public class HomeRoomAdapter extends RecyclerView.Adapter<HomeRoomAdapter.ViewHolder> {
    private Context context;
    private List<Room> arrListRoom;
    private int layout;

    public HomeRoomAdapter(Context context, List<Room> arrListRoom, int layout) {
        this.context = context;
        this.arrListRoom = arrListRoom;
        this.layout = layout;
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
        holder.txtCategories.setText(room.getCategoriesRoom());
        holder.txtTitleRoom.setText(room.getTitle());
        holder.txtPriceRoom.setText(room.getPrice());
        holder.txtAddressRoom.setText(room.getAddress());
    }

    @Override
    public int getItemCount() {
        return arrListRoom.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageHomeRoom;
        TextView txtCategories,txtTitleRoom, txtPriceRoom,txtAddressRoom;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageHomeRoom = itemView.findViewById(R.id.imgHomeRoom);
            txtCategories = itemView.findViewById(R.id.txtCategoriesHome);
            txtTitleRoom = itemView.findViewById(R.id.txtTitleHomeRoom);
            txtPriceRoom = itemView.findViewById(R.id.txtPriceHomeRoom);
            txtAddressRoom = itemView.findViewById(R.id.txtAddressHomeRoom);
        }
    }
}
