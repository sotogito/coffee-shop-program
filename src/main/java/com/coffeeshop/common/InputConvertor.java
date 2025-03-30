package com.coffeeshop.common;

public class InputConvertor {
    public static int stringToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("숫자로 입력해주세요.");
        }
    }
}
