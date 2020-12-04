package com.longkhoa.fpolyfindroom.view.favorite;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.adapter.BannerAdapter;
import com.longkhoa.fpolyfindroom.adapter.FavoriteAdapter;
import com.longkhoa.fpolyfindroom.model.Banner;
import com.longkhoa.fpolyfindroom.model.Favorite;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {

    //Fragment la oncreateView
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//<<<<<<< HEAD
//        View view = inflater.inflate(R.layout.custom_item_room, container, false);
//=======
        View view = inflater.inflate(R.layout.favorite_fragment, container, false);
//        RecyclerView recyclerView_favorite = view.findViewById(R.id.recyclerview_favorite);
//        LinearLayoutManager linearLayoutManager_favorite = new LinearLayoutManager(getActivity());
//        linearLayoutManager_favorite.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView_favorite.setLayoutManager(linearLayoutManager_favorite);
//        recyclerView_favorite.setItemAnimator(new DefaultItemAnimator());
//        ArrayList<Favorite> arrayList_favorite = new ArrayList<>();
//        arrayList_favorite.add(new Favorite(R.drawable.banner,R.drawable.outline_favorite_border_white_24dp,"abc","hjkl","vbn"));
//        arrayList_favorite.add(new Favorite(R.drawable.banner,R.drawable.outline_favorite_border_white_24dp,"abc","hjkl","vbn"));
//        FavoriteAdapter favoriteAdapter = new FavoriteAdapter(arrayList_favorite,getActivity());
//        recyclerView_favorite.setAdapter(favoriteAdapter);
//>>>>>>> a5a166a5cb55f4029f3f524bd6397450b2a9e7e8
        return view;
    }
}