package com.longkhoa.fpolyfindroom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.model.Util;

import java.util.List;

public class UtilDetailRoomAdapter extends  RecyclerView.Adapter<UtilDetailRoomAdapter.ViewHolder>{
    private List<String> arrUtil;
    private Context context;
    private int layout;

    public UtilDetailRoomAdapter(List<String> arrUtil, Context context, int layout) {
        this.arrUtil = arrUtil;
        this.context = context;
        this.layout = layout;
    }

    @NonNull
    @Override
    public UtilDetailRoomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UtilDetailRoomAdapter.ViewHolder holder, int position) {
        holder.txtNameUtil.setText(arrUtil.get(position).substring(10));
    }

    @Override
    public int getItemCount() {
        return arrUtil.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNameUtil;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameUtil = itemView.findViewById(R.id.txtNameUtilDetailRoom);
        }
    }
}
