package com.longkhoa.fpolyfindroom.view.auth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.presenter.LoginInterface;
import com.longkhoa.fpolyfindroom.presenter.LoginPresenter;
import com.longkhoa.fpolyfindroom.view.activity.DashBoardActivity;

import es.dmoral.toasty.Toasty;

public class LoginFragment extends Fragment implements LoginInterface {
    Button btnLogin;
    EditText edtUserName,edtPassword;
    LoginPresenter loginPresenter;

    TextView tvQuenMatKhau;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        btnLogin = view.findViewById(R.id.btnDangNhap);
//        edtPassword = view.findViewById(R.id.edtPasswordd);
        edtUserName = view.findViewById(R.id.edtUserName);
        loginPresenter = new LoginPresenter(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),DashBoardActivity.class));
//                startActivity(new Intent(getActivity(),DashBoardActivity.class));
                Log.d("LOIIII",edtUserName.getText().toString());
            }
        });
        return view;
    }

    @Override
    public void loginSuccess() {
//        Toasty.success(getActivity(),"Login Thành công",Toasty.LENGTH_SHORT).show();
        startActivity(new Intent(getActivity(),DashBoardActivity.class));
    }

    @Override
    public void loginFail(String message) {
        Log.d("LOIIII",message.toString());
        startActivity(new Intent(getActivity(),DashBoardActivity.class));
        Toasty.error(getActivity(),message,Toasty.LENGTH_SHORT).show();
    }
}