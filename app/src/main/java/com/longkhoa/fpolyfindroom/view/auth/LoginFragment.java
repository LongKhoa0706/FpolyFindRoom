package com.longkhoa.fpolyfindroom.view.auth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.model.MyStatusUser;
import com.longkhoa.fpolyfindroom.presenter.auth.LoginInterface;
import com.longkhoa.fpolyfindroom.presenter.auth.LoginPresenter;
import com.longkhoa.fpolyfindroom.util.Constant;
import com.longkhoa.fpolyfindroom.view.activity.DashBoardActivity;

import es.dmoral.toasty.Toasty;

import static android.content.Context.MODE_PRIVATE;

public class LoginFragment extends Fragment implements LoginInterface {
    Button btnLogin;
    EditText edtUserName,edtPassword;
    LoginPresenter loginPresenter;
    TextView tvQuenMatKhau;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        SharedPreferences sharedPref = context.getSharedPreferences("user", Context.MODE_PRIVATE);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        btnLogin = view.findViewById(R.id.btnDangNhap);
        edtPassword = view.findViewById(R.id.edtPasswordLogin);
        edtUserName = view.findViewById(R.id.edtEmail);
        loginPresenter = new LoginPresenter(this);
        edtUserName.setText("09030921");
        edtPassword.setText("longkhoa");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenter.login(edtUserName.getText().toString(),edtPassword.getText().toString());
            }
        });
        return view;
    }
    @Override
    public void loginSuccess(MyStatusUser myStatus) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constant.KEY_ACCOUNT, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myStatus.getUser());
        editor.putString("user", json);
        editor.apply();
        Toasty.success(getActivity(),myStatus.getMes(),Toasty.LENGTH_SHORT).show();
        startActivity(new Intent(getActivity(), DashBoardActivity.class));
    }
    @Override
    public void loginFail(String message) {
        Log.d("LOIIII",message.toString());
        Toasty.error(getActivity(),message,Toasty.LENGTH_SHORT).show();
    }
}

