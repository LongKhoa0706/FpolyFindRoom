//package com.longkhoa.fpolyfindroom.adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.cardview.widget.CardView;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.android.material.card.MaterialCardView;
//import com.longkhoa.fpolyfindroom.R;
//import com.longkhoa.fpolyfindroom.model.Home;
//import com.longkhoa.fpolyfindroom.model.Room;
//import com.longkhoa.fpolyfindroom.view.room.CallbackRoomAdapter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {
//    private List<Room> roomList;
//    private Context context;
//    private int layout;
//    private CallbackRoomAdapter callbackRoomAdapter;
//
//    public RoomAdapter(List<Room> roomList, Context context, int layout, CallbackRoomAdapter callbackRoomAdapter) {
//        this.roomList = roomList;
//        this.context = context;
//        this.layout = layout;
//        this.callbackRoomAdapter = callbackRoomAdapter;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(context);
//        View view = layoutInflater.inflate(layout, parent, false);
//        return new RoomAdapter.ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Room room = roomList.get(position);
//        holder.txtCategories.setText(room.getType());
//        holder.txtTitleRoom.setText(room.getTitle());
//        holder.txtPriceRoom.setText(String.valueOf(room.getPrice()+"đ"));
//        holder.txtAddressRoom.setText(room.getLocation());
//
//        holder.materialCardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                callbackRoomAdapter.onClickListenerCardView(room);
//            }
//        });
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return roomList.size();
//    }
//
//    class ViewHolder extends RecyclerView.ViewHolder {
//        ImageView imageHomeRoom,imgIconHomeRoom;
//        TextView txtCategories,txtTitleRoom, txtPriceRoom,txtAddressRoom;
//        MaterialCardView materialCardView;
//        ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            imageHomeRoom = itemView.findViewById(R.id.imgHomeRoom);
//            txtCategories = itemView.findViewById(R.id.txtCategoriesHome);
//            txtTitleRoom = itemView.findViewById(R.id.txtTitleHomeRoom);
//            txtPriceRoom = itemView.findViewById(R.id.txtPriceHomeRoom);
//            txtAddressRoom = itemView.findViewById(R.id.txtAddressHomeRoom);
//            imgIconHomeRoom = itemView.findViewById(R.id.imgIconHomeRoom);
//            materialCardView = itemView.findViewById(R.id.cardViewItem);
//        }
//    }
//}
//
