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
import com.longkhoa.fpolyfindroom.model.CategoriesRoom;

import java.util.List;

public class CateroriesAdapter extends RecyclerView.Adapter<CateroriesAdapter.ViewHolder> {
    private Context context;
    private List<CategoriesRoom> arrayCategoies;
    private int layout;

    public CateroriesAdapter(Context context, List<CategoriesRoom> arrayCategoies, int layout) {
        this.context = context;
        this.arrayCategoies = arrayCategoies;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull CateroriesAdapter.ViewHolder holder, int position) {
        CategoriesRoom categories = arrayCategoies.get(position);
        holder.txtTitleIcon.setText(categories.getTitle());
        holder.imageIconCategories.setImageResource(categories.getIcon());
    }

    @Override
    public int getItemCount() {
        return arrayCategoies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageIconCategories;
        TextView txtTitleIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageIconCategories = itemView.findViewById(R.id.imageIconCategories);
            txtTitleIcon = itemView.findViewById(R.id.txtTitleIcon);
        }
    }
}
