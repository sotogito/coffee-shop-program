package com.coffeeshop.user.common;

import com.coffeeshop.common.InputConvertor;
import com.coffeeshop.common.Printout;

import java.util.Arrays;

public enum UserFunction {
    LOGIN(1, "로그인"),
    JOIN(2, "회원가입"),
    EXIT(0, "종료하기");

    private final int number;
    private final String description;

    UserFunction(int number, String description) {
        this.number = number;
        this.description = description;
    }

    public int getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    public static UserFunction find(String input) {
        int inputNumber = InputConvertor.stringToInt(input);

        return Arrays.stream(UserFunction.values())
                .filter(f -> f.number == inputNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저 기능입니다."));
    }

    @Override
    public String toString() {
        return String.format(Printout.FUNCTION,number,description);
    }
}
