package com.coffeeshop.user.controller;

import com.coffeeshop.user.model.dto.User;
import com.coffeeshop.user.service.LoginService;

public class LoginController {
    private final LoginService loginService = new LoginService();

    public User login(User user) {
        return loginService.login(user);
    }




}
