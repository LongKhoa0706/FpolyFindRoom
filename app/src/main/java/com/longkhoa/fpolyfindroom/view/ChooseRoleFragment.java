package com.longkhoa.fpolyfindroom.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.util.Constant;
import com.longkhoa.fpolyfindroom.view.auth.OptionAuthFragment;
import com.longkhoa.fpolyfindroom.view.auth.RegisterFragment;


public class ChooseRoleFragment extends Fragment {
    AppCompatButton btnInkeeper,btnCustomer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_role, container, false);
        btnInkeeper = view.findViewById(R.id.btnInkeeper);
        btnCustomer = view.findViewById(R.id.btnCustomer);
        btnCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constant.KEY_CHOOSE_ROLE, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("customer","customer");
                editor.commit();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameClient, new OptionAuthFragment()).addToBackStack(null).commit();

            }
        });
        btnInkeeper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constant.KEY_CHOOSE_ROLE, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("customer","innkeeper");
                editor.commit();

                getActivity(). getSupportFragmentManager().beginTransaction().replace(R.id.frameClient, new OptionAuthFragment()).addToBackStack(null).commit();
            }
        });
        return view;
    }
}