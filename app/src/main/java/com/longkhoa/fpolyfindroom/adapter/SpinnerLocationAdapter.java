package com.longkhoa.fpolyfindroom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.model.City;

import java.util.List;

public class SpinnerLocationAdapter extends BaseAdapter {
    List<City> listLocationCity;
    Context context;
    int layout;

    public SpinnerLocationAdapter(List<City> listLocationCity, Context context, int layout) {
        this.listLocationCity = listLocationCity;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return listLocationCity.size()+1;
    }

    @Override
    public Object getItem(int i) {
        return listLocationCity.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
         view = layoutInflater.inflate(layout, null, false);
       TextView txtLocation = view.findViewById(R.id.txtItemLocation);
       City city = listLocationCity.get(i);
       txtLocation.setText(city.getTitle());

        return view;
    }
}
