package com.longkhoa.fpolyfindroom.presenter;

// Interface là nơi mà đưa ra kết quả cho thằng view
public interface RegisterInterface  {
    void registerSuccess();
    void registerFail(String message);
    void showLoading();
    void hideLoading();
}
