package com.longkhoa.fpolyfindroom.presenter.auth;

import com.longkhoa.fpolyfindroom.model.MyStatusUser;

public interface LoginInterface {
    void loginSuccess(MyStatusUser myStatus);
    void loginFail(String message);
}
