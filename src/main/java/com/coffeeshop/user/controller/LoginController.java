package com.coffeeshop.user.controller;

import com.coffeeshop.user.model.dto.User;
import com.coffeeshop.user.service.LoginService;
import com.coffeeshop.user.view.OutputView.LoginOutputView;

public class LoginController {
    private final LoginService loginService = new LoginService();
    private final LoginOutputView loginOutputView = new LoginOutputView();

    public User login(User user) {
        return loginService.login(user);
    }

    public User join(User user) {
        return loginService.join(user);
    }


}
