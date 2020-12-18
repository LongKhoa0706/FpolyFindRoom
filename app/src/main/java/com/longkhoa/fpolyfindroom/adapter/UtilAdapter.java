package com.longkhoa.fpolyfindroom.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.model.Util;
import com.longkhoa.fpolyfindroom.view.util.CallBackUtilAdapter;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class UtilAdapter extends RecyclerView.Adapter<UtilAdapter.ViewHolder> {
    private List<Util> arrUtil;
    private Context context;
    private int layout;
    private CallBackUtilAdapter callBackUtilAdapter;




    public UtilAdapter(List<Util> arrUtil, Context context, int layout,CallBackUtilAdapter callBackUtilAdapter) {
        this.arrUtil = arrUtil;
        this.context = context;
        this.layout = layout;
        this.callBackUtilAdapter = callBackUtilAdapter;
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
        holder.imgCheck.setVisibility(View.GONE);
        holder.cardItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBackUtilAdapter.onClickListenerCardView(util);
//                util.setCheck(true);
                holder.imgCheck.setVisibility(View.VISIBLE);
//                Gson gson = new Gson();
//                String jsonText = gson.toJson(arrUtil.get(position));
//                editor.putString("utils", jsonText);
//                editor.apply();
            }
        });
        holder.cardItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                util.setCheck(false);
                holder.imgCheck.setVisibility(View.GONE);

                return false;
            }
        });
//        holder.cardItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               util.setCheck(true);
//               if (util.isCheck()){
//                   holder.imgCheck.setVisibility(View.VISIBLE);
//                   Log.d("DA CHECK","DA CHECK");
//                   Toast.makeText(context, "DA check", Toast.LENGTH_SHORT).show();
//               }else {
//                   Toast.makeText(context, "CHUA", Toast.LENGTH_SHORT).show();
//               }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return arrUtil.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgIcon,imgCheck;
        LinearLayout cardItem;
        TextView txtTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.imgIcon);
            txtTitle = itemView.findViewById(R.id.txtTitleUtil);
            cardItem = itemView.findViewById(R.id.cardItem);
            imgCheck = itemView.findViewById(R.id.imgCheckUtil);

        }
    }

}
