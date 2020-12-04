package com.longkhoa.fpolyfindroom.presenter.user;

import com.longkhoa.fpolyfindroom.networking.RetrofitClient;
import com.longkhoa.fpolyfindroom.service.UserService;

public class UserPresenter {
    private UserService userService;
    private UserInterface userInterface;

    public UserPresenter(UserInterface userInterface) {
        this.userInterface = userInterface;
    }
    public void  getProfile(){
        userService = RetrofitClient.getRetrofitInstance().create(UserService.class);

    }
}
