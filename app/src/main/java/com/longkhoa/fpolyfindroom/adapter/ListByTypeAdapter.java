package com.longkhoa.fpolyfindroom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.card.MaterialCardView;
import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.model.Room;
import com.longkhoa.fpolyfindroom.view.room.CallbackRoomAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListByTypeAdapter  extends RecyclerView.Adapter<ListByTypeAdapter.ViewHolder>{
    private List<Room> arrListTypeRoom;
    private Context context;
    List<SlideModel> listImage = new ArrayList<SlideModel>();
    private int layout;
    private CallbackRoomAdapter callbackRoomAdapter;

    public ListByTypeAdapter(List<Room> arrListTypeRoom, Context context, int layout, CallbackRoomAdapter callbackRoomAdapter) {
        this.arrListTypeRoom = arrListTypeRoom;
        this.context = context;
        this.layout = layout;
        this.callbackRoomAdapter = callbackRoomAdapter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListByTypeAdapter.ViewHolder holder, int position) {
        Room room = arrListTypeRoom.get(position);
        listImage.add(new SlideModel(room.getImage().get(position), ScaleTypes.CENTER_CROP));
        holder.imageSlider.setImageList(listImage);
        holder.txtPrice.setText(room.getPrice()+"đ");
        holder.txtTitle.setText(room.getTitle());
        holder.txtAmountBath.setText(room.getAmount_bathroom()+"");
        holder.txtAmountBed.setText(room.getAmount_bedroom()+"");
        holder.txtLocatio.setText(room.getLocation());


        holder.materialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callbackRoomAdapter.onClickListenerCardView(room);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrListTypeRoom.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageSlider imageSlider;
        MaterialCardView materialCardView;
        TextView txtPrice,txtTitle,txtAmountBed,txtAmountBath,txtLocatio;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            materialCardView = itemView.findViewById(R.id.cardViewTypeRoom);
            imageSlider = itemView.findViewById(R.id.image_slider_item);
            txtPrice = itemView.findViewById(R.id.txtItemPriceTypeRoom);
            txtTitle = itemView.findViewById(R.id.txtTypeRoom);
            txtAmountBath = itemView.findViewById(R.id.txtAmountBathRoom);
            txtLocatio = itemView.findViewById(R.id.txtLocationTypeRoom);
            txtAmountBed = itemView.findViewById(R.id.txtAmountBedRoom);
        }
    }
}
