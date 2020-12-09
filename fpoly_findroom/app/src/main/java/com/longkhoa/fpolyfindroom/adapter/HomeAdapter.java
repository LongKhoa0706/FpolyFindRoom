package com.longkhoa.fpolyfindroom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.model.Home;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private List<Home> homeList;
    private Context context;


    public HomeAdapter(ArrayList<Home> homeList , Context context){
        this.context = context;
        this.homeList = homeList;
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
        Home home = homeList.get(position);
        holder.image_home.setImageResource(home.getImage_home());
        holder.image_favorite.setImageResource(home.getImage_favorite());
        holder.txt_loai.setText(home.getTxt_loai());
        holder.txt_title.setText(home.getTxt_title());
        holder.txt_price.setText(home.getTxt_price());
        holder.image_home1.setImageResource(home.getImage_home1());
        holder.image_favorite1.setImageResource(home.getImage_favorite1());
        holder.txt_loai1.setText(home.getTxt_loai1());
        holder.txt_title1.setText(home.getTxt_title1());
        holder.txt_price1.setText(home.getTxt_price1());
//        holder.cardviewHome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, DetailRoomFragment.class);
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return homeList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardviewHome;
        ImageView image_home, image_home1, image_favorite, image_favorite1;
        TextView txt_loai, txt_title, txt_price, txt_loai1, txt_title1, txt_price1;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_home =itemView.findViewById(R.id.image_room);
            image_favorite = itemView.findViewById(R.id.favorite);
            image_home1 = itemView.findViewById(R.id.image_room1);
            image_favorite1 = itemView.findViewById(R.id.favorite1);
            txt_loai=itemView.findViewById(R.id.txt_loai);
            txt_loai1=itemView.findViewById(R.id.txt_loai1);
            txt_price=itemView.findViewById(R.id.txt_price);
            txt_price1=itemView.findViewById(R.id.txt_price1);
            txt_title=itemView.findViewById(R.id.txt_title);
            txt_title1=itemView.findViewById(R.id.txt_title1);
            cardviewHome=itemView.findViewById(R.id.cardview_home);
        }
    }
}

