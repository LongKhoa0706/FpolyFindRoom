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
import com.longkhoa.fpolyfindroom.model.Util;

import java.util.List;

public class UtilAdapter extends RecyclerView.Adapter<UtilAdapter.ViewHolder> {
    private List<Util> arrUtil;
    private Context context;
    private int layout;


    public UtilAdapter(List<Util> arrUtil, Context context, int layout) {
        this.arrUtil = arrUtil;
        this.context = context;
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
        Util util = arrUtil.get(position);
        holder.imgIcon.setImageResource(util.getIcon());
        holder.txtTitle.setText(util.getTitle());

    }

    @Override
    public int getItemCount() {
        return arrUtil.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgIcon;
        TextView txtTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.imgIcon);
            txtTitle = itemView.findViewById(R.id.txtTitleUtil);
        }
    }
}
