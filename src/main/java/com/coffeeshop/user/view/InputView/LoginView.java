package com.coffeeshop.user.view.InputView;

import com.coffeeshop.user.common.UserFunction;
import com.coffeeshop.user.common.UserType;
import com.coffeeshop.user.controller.LoginController;
import com.coffeeshop.user.model.dto.User;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

public class LoginView {
    private final LoginController loginController = new LoginController();
    private final Scanner sc = new Scanner(System.in);

    public User run() {
        User user = null;
        UserFunction function;

        while (true) {
            Arrays.stream(UserFunction.values()).forEach(System.out::print);
            try {
                function = UserFunction.find(sc.nextLine());

                if (function.equals(UserFunction.EXIT)) {
                    return user;
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return switch (function) {
            case LOGIN -> login();
            case JOIN -> join();
            default -> user;
        };
    }

    public User login() {
        System.out.println("===== 로그인 =====");
        System.out.println("아이디, 비번을 입력해주세요.");
        String id = sc.nextLine();
        String password = sc.nextLine();

        User user = User.builder()
                .userId(id)
                .userPassword(password)
                .build();

        return loginController.login(user);
    }


    public User join() {
        System.out.println("===== 회원가입 =====");
        System.out.println("아이디, 비번을 입력해주세요.");
        String id = sc.nextLine();
        String password = sc.nextLine();
        System.out.println("이름, 주소, 전화번호(선택) 을 입력해주세요.");
        String name = sc.nextLine();
        String address = sc.nextLine();
        String phone = sc.nextLine();

        User user = User.builder()
                .userId(id)
                .userPassword(password)
                .userName(name)
                .address(address)
                .phone(phone)
                .userType(UserType.USER)
                .build();

        return user;

    }


}