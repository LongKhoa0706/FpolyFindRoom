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

import java.util.ArrayList;
import java.util.List;

public class DistrictAdapter extends RecyclerView.Adapter<DistrictAdapter.ViewHolder> {
    private List<District> districtList;
    private Context context;


    public DistrictAdapter(ArrayList<District> districtList , Context context){
        this.context = context;
        this.districtList = districtList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.cardview_district,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        District district = districtList.get(position);
        holder.image_district.setImageResource(district.getImage_district());
        holder.txt_title.setText(district.getTxt2());
    }

    @Override
    public int getItemCount() {
        return districtList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_district;
        TextView txt_title;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
//            image_district = itemView.findViewById(R.id.image_district);
            txt_title = itemView.findViewById(R.id.txt2);
        }
    }
}

