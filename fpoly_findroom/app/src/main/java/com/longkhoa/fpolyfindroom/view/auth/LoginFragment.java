package com.longkhoa.fpolyfindroom.view.auth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.view.activity.DashBoardActivity;

public class LoginFragment extends Fragment {
    Button btnLogin;
    TextView tvQuenMatKhau;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        btnLogin = view.findViewById(R.id.btnDangNhap);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), DashBoardActivity.class));
//                overridePendingTransition(0,0);
//                Toast.makeText(LoginActivity.this,"Đăng nhạp thành công!!!",Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}