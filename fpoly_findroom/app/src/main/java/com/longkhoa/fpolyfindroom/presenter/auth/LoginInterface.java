package com.longkhoa.fpolyfindroom.presenter.auth;

import com.longkhoa.fpolyfindroom.model.MyStatus;

public interface LoginInterface {
    void loginSuccess(MyStatus myStatus);
    void loginFail(String message);
}
