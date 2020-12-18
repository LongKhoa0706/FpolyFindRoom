package com.longkhoa.fpolyfindroom.view.room;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.adapter.UtilDetailRoomAdapter;
import com.longkhoa.fpolyfindroom.model.Room;
import com.longkhoa.fpolyfindroom.util.Constant;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailRoomFragment extends Fragment {
    private Room room;
    TextView txtType,txtTitle,txtPrice,txtDate,txtDesc,txtLocation,txtAuthor;
    ImageView imgIconType;
    CircleImageView imgAuthor;
    UtilDetailRoomAdapter utilDetailRoomAdapter;
    RecyclerView recyclerViewUtil;
    CarouselView carouselView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detailroom_fragment, container, false);
        txtType = view.findViewById(R.id.txtTypeDetailRoom);
        carouselView = view.findViewById(R.id.carouselView);
        txtAuthor = view.findViewById(R.id.txtAuthorDetailRoom);
        txtTitle = view.findViewById(R.id.txtTitleDetailRoom);
        txtPrice = view.findViewById(R.id.txtPriceDetailRoom);
        recyclerViewUtil = view.findViewById(R.id.reyclerViewUtilDetailRoom);
        txtDate = view.findViewById(R.id.txtDateDetailRoom);
        txtDesc = view.findViewById(R.id.txtDescDetailRoom);
        imgIconType = view.findViewById(R.id.imgIconDetailRoom);
        txtLocation = view.findViewById(R.id.txtLocationDetailRoom);
        Bundle bundle = getArguments();
        if (bundle != null){
            room = bundle.getParcelable(Constant.KEY_BUNDLE_ROOM);
            switch (room.getType()){
                case "nhà trọ":
                    imgIconType.setImageResource(R.drawable.baseline_home_black_24dp);
                    break;
                case "căn hộ":
                    imgIconType.setImageResource(R.drawable.baseline_apartment_black_24dp);
                    break;
                case "phòng":
                    imgIconType.setImageResource(R.drawable.baseline_meeting_room_black_24dp);
                    break;
            }
            txtTitle.setText(room.getTitle());
//            txtAuthor.setText(room.getUser().getUserName());
            txtLocation.setText(room.getLocation());
            txtDesc.setText(room.getDescription());
            txtPrice.setText(room.getPrice() +"đ");
            txtDate.setText(room.getCreatedAt());
            txtType.setText(room.getType());
            txtAuthor.setText(room.getUser().getName());
            Context context;
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
            recyclerViewUtil.setLayoutManager(gridLayoutManager);


            utilDetailRoomAdapter = new UtilDetailRoomAdapter(room.getUtilities(),getActivity(),R.layout.custom_item_util_detail_room);
            recyclerViewUtil.setAdapter(utilDetailRoomAdapter);
            carouselView.setPageCount(room.getImage().size());
            carouselView.setImageListener(new ImageListener() {
                @Override
                public void setImageForPosition(int position, ImageView imageView) {
                    Glide.with(getActivity())
                            .load(room.getImage().get(position))
                            .into(imageView);

                }
            });

        }else {
            Log.d("DETAILL","NULL");
        }
        return view;
    }


}

