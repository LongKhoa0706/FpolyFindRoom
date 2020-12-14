package com.longkhoa.fpolyfindroom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.longkhoa.fpolyfindroom.R;

import java.util.List;

public class BannerAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    List<Integer> arrImage;


    public BannerAdapter(Context context, List<Integer> arrImage) {
        this.context = context;
        this.arrImage = arrImage;

    }


//    public int [] slideBanner = {
//            R.drawable.banner1,
//            R.drawable.banner2,
//            R.drawable.banner3,
//    };
    @Override
    public int getCount() {
        return arrImage.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView layoutImgae;

        layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.cardview_banner, null, false);
        layoutImgae = v.findViewById(R.id.image_banner);


        Glide
                .with(context)
                .load(arrImage.get(position))
                .into(layoutImgae);


        container.addView(v);

        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);

    }
}

