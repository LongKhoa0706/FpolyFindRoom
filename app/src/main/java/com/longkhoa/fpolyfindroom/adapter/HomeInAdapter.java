package com.longkhoa.fpolyfindroom.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.model.Room;
import com.longkhoa.fpolyfindroom.view.CallBackItemOption;
import com.longkhoa.fpolyfindroom.view.room.CallbackRoomAdapter;

import java.text.DecimalFormat;
import java.util.List;

public class HomeInAdapter extends RecyclerView.Adapter<HomeInAdapter.ViewHolder> {
    private Context context;
    private List<Room> arrListRoom;
    private int layout;
    private CallbackRoomAdapter callbackRoomAdapter;
    private CallBackItemOption callBackItemOption;


    public HomeInAdapter(Context context, List<Room> arrListRoom, int layout, CallbackRoomAdapter callbackRoomAdapter,CallBackItemOption callBackItemOption) {
        this.context = context;
        this.arrListRoom = arrListRoom;
        this.layout = layout;
        this.callbackRoomAdapter = callbackRoomAdapter;
        this.callBackItemOption = callBackItemOption;
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
        holder.txtItemPriceRoom.setText(numberFormat(room.getPrice()+"")+"đ");
        holder.txtItemCategoriRoom.setText(room.getType());
        holder.txtItemTitleRoom.setText(room.getTitle());
        Glide.with(context).load(room.getImage().get(0)).placeholder(R.drawable.loading).into(holder.imgRoom);
        holder.imgOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBackItemOption.onClickItem(room);
            }
        });
        if (room.isStatus() == true){
            holder.linearLayoutColor.setBackgroundColor(Color.parseColor("#2eb82e"));
            holder.txtStatusRoom.setText("Đã duyệt");
        }else {
            holder.linearLayoutColor.setBackgroundColor(Color.parseColor("#ff4d4d"));
            holder.txtStatusRoom.setText("Đang duyệt");
        }
//
        holder.materialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callbackRoomAdapter.onClickListenerCardView(room);
            }
        });
        holder.txtItemAddress.setText(room.getLocation());
    }

    @Override
    public int getItemCount() {
        return arrListRoom.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgdiachi,imgOption,imgRoom;
        TextView txtItemPriceRoom,txtItemCategoriRoom,txtItemTitleRoom,txtItemAddress,txtStatusRoom;
        MaterialCardView materialCardView;
        LinearLayout linearLayoutColor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgOption = itemView.findViewById(R.id.imgOption);
            imgdiachi=itemView.findViewById(R.id.imgdiachi);
            imgRoom = itemView.findViewById(R.id.imgHomeRoomManager);
            linearLayoutColor = itemView.findViewById(R.id.lineColor);
            txtStatusRoom = itemView.findViewById(R.id.txtStatusManageRoom);
//            imgItemRoom=itemView.findViewById(R.id.imgItemRoom);
            txtItemPriceRoom=itemView.findViewById(R.id.txtItemPriceRoom);
            txtItemCategoriRoom=itemView.findViewById(R.id.txtItemCategoriRoom);
            txtItemTitleRoom=itemView.findViewById(R.id.txtItemTitleRoom);
            txtItemAddress=itemView.findViewById(R.id.txtAddressManager);
            materialCardView = itemView.findViewById(R.id.cardViewRoomManager);
        }
    }
    public static String numberFormat(String input) {
        String newString = "";
        DecimalFormat formatter = new DecimalFormat("#,###");
        if (null != input && input.length() > 0) {
            if (input.contains(".") && !input.contains(",")) {
                int endIndex = input.lastIndexOf(".");
                if (endIndex != -1) {
                    newString = formatter.format(Long.parseLong(input.substring(0, endIndex)));
                }
            } else if (input.contains(".") && input.contains(",")) {
                int endIndex = input.lastIndexOf(".");
                newString = input.substring(0, endIndex);
            } else if (input.contains(",") || input.contains(" ")) {
                newString = input;
            } else {
                newString = formatter.format(Long.parseLong(input));
            }
        }
        return newString;
    }
}
