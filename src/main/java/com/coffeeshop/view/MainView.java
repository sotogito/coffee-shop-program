package com.coffeeshop.view;

import com.coffeeshop.user.model.dto.User;
import com.coffeeshop.user.view.InputView.LoginView;

public class MainView {
    private final LoginView loginView = new LoginView();

    public void run() {
        User user = null;
        while (true){
            try {
                user = loginView.run();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        if (user == null) {
            System.out.println("종료합니다.");
            return;
        }

        System.out.println(user.toString());
    }
}
