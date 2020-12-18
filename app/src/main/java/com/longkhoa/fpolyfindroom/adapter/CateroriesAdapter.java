package com.longkhoa.fpolyfindroom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.model.CategoriesRoom;
import com.longkhoa.fpolyfindroom.view.CallBackAdapterType;

import java.util.List;

public class CateroriesAdapter extends RecyclerView.Adapter<CateroriesAdapter.ViewHolder> {
    private Context context;
    private List<CategoriesRoom> arrCategories;
    private int layout;
    private CallBackAdapterType callBackAdapterType;

    public CateroriesAdapter(Context context, List<CategoriesRoom> arrayCategoies, int layout,CallBackAdapterType callBackAdapterType) {
        this.context = context;
        this.arrCategories = arrayCategoies;
        this.layout = layout;
        this.callBackAdapterType = callBackAdapterType;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull CateroriesAdapter.ViewHolder holder, int position) {
        CategoriesRoom categories = arrCategories.get(position);
        holder.txtTitleIcon.setText(categories.getTitle());
        holder.imageIconCategories.setImageResource(categories.getIcon());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBackAdapterType.setOnItemClick(categories);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrCategories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageIconCategories;
        TextView txtTitleIcon;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.cardViewType);
            imageIconCategories = itemView.findViewById(R.id.imageIconCategories);
            txtTitleIcon = itemView.findViewById(R.id.txtTitleIcon);
        }
    }
}
