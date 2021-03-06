package com.longkhoa.fpolyfindroom.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;
import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.model.MyStatusRoom;
import com.longkhoa.fpolyfindroom.model.Room;
import com.longkhoa.fpolyfindroom.view.room.CallbackRoomAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeRoomAdapter extends RecyclerView.Adapter<HomeRoomAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Room> arrListRoom;
    private int layout;
    private CallbackRoomAdapter callbackRoomAdapter;

    public HomeRoomAdapter(Context context, ArrayList<Room> arrListRoom, int layout, CallbackRoomAdapter callbackRoomAdapter) {
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
        switch (room.getType()){
            case "nhà trọ":
                holder.imgIconHomeRoom.setImageResource(R.drawable.baseline_home_white_18dp);
                break;
            case "căn hộ":
                holder.imgIconHomeRoom.setImageResource(R.drawable.baseline_apartment_white_18dp);
                break;
            case "phòng":
                holder.imgIconHomeRoom.setImageResource(R.drawable.baseline_meeting_room_white_18dp);
                break;
        }
        holder.txtCategories.setText(room.getType());
        holder.txtTitleRoom.setText(room.getTitle());
        holder.txtPriceRoom.setText(String.valueOf(room.getPrice()+"đ"));
        holder.txtAddressRoom.setText(room.getLocation());
        Glide.with(context).load(room.getImage().get(0)).into(holder.imageHomeRoom);
        holder.materialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               callbackRoomAdapter.onClickListenerCardView(room);
            }
        });
        holder.imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveArrayList(arrListRoom,"a");
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrListRoom.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageHomeRoom,imgIconHomeRoom,imgFavorite;

        TextView txtCategories,txtTitleRoom, txtPriceRoom,txtAddressRoom;
        MaterialCardView materialCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFavorite = itemView.findViewById(R.id.img_favorite);
            imageHomeRoom = itemView.findViewById(R.id.imgHomeRoom);
            txtCategories = itemView.findViewById(R.id.txtCategoriesHome);
            txtTitleRoom = itemView.findViewById(R.id.txtTitleHomeRoom);
            txtPriceRoom = itemView.findViewById(R.id.txtPriceHomeRoom);
            txtAddressRoom = itemView.findViewById(R.id.txtAddressHomeRoom);
            imgIconHomeRoom = itemView.findViewById(R.id.imgIconHomeRoom);
            materialCardView = itemView.findViewById(R.id.cardViewItem);
        }
    }
    public void saveArrayList(ArrayList<Room> list, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }
}
