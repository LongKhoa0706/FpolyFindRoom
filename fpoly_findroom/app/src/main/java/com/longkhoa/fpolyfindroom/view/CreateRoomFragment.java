package com.longkhoa.fpolyfindroom.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.longkhoa.fpolyfindroom.R;


public class CreateRoomFragment extends Fragment {
    LinearLayout linearAddUtil;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_room, container, false);
        linearAddUtil = view.findViewById(R.id.linearAddUtil);
        linearAddUtil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListUtilFragment listUtilFragment = new ListUtilFragment();
                getFragmentManager().beginTransaction().replace(R.id.dashboard_frame, listUtilFragment).addToBackStack(null).commit();
            }
        });
        return view;
    }
}