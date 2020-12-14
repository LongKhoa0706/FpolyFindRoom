package com.longkhoa.fpolyfindroom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.longkhoa.fpolyfindroom.R;

public class SlideAdapter  extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public SlideAdapter(Context context) {
        this.context = context;

    }

    public  String [] slideTitle = {
            "Chào mừng bạn đến với Findroom, nơi có trải nghiệm thuê phòng tốt nhất",
            "Việc tìm kiếm một căn phòng phù hợp với bạn không còn là vấn đề nữa",
            "Hãy để chúng tôi giúp bạn hoàn thành mong ước của mình cùng với Find ❤"
    };

    public int [] slideImage = {
            R.drawable.slide1,
            R.drawable.slide2,
            R.drawable.slide3,
    };

    @Override
    public int getCount() {
        return slideTitle.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView layoutImgae;
        TextView txtTitle;

        layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.item_layout, null, false);
        layoutImgae = v.findViewById(R.id.imageSlide);
        txtTitle = v.findViewById(R.id.txtSlide);

        Glide
                .with(context)
                .load(slideImage[position])
                .into(layoutImgae);
        txtTitle.setText(slideTitle[position]);

        container.addView(v);

        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);

    }
}
