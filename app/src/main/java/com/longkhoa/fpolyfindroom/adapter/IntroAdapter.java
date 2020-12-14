package com.longkhoa.fpolyfindroom.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.model.Intro;

import java.util.ArrayList;
import java.util.List;

public class IntroAdapter extends RecyclerView.Adapter<IntroAdapter.ViewHolder> {
    private List<Intro> introList;
    private Context context;


    public IntroAdapter(ArrayList<Intro> introList , Context context){
        this.context = context;
        this.introList = introList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.cardview_intro,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Intro intro = introList.get(position);
        holder.image_intro.setImageResource(intro.getImage_intro());
        holder.txt_title.setText(intro.getTxt_title());
    }

    @Override
    public int getItemCount() {
        return introList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_intro;
        TextView txt_title;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_intro = itemView.findViewById(R.id.image_intro);
            txt_title = itemView.findViewById(R.id.txt_title);
        }
    }
}

