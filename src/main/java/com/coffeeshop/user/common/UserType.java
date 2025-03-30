package com.coffeeshop.user.common;

import java.util.Arrays;

public enum UserType {
    ADMIN("admin"),
    USER("user")
    ;

    private final String value;

    UserType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static UserType find(String value){
        return Arrays.stream(UserType.values())
                .filter(t -> t.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원 타입"));
    }

}

