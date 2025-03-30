package com.coffeeshop.user.model.dto;

import com.coffeeshop.user.common.UserType;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class User {
    private int userNumber;
    private String userId;
    private String userPassword;
    private String userName;
    private String address;
    private String phone;
    private UserType userType;

    /**
     * MyBatis는 내부적으로 mapper에서 기본생성자 + setter로 객체를 생성하여 반환한다.
     */
    public void setUserType(String userType){
        this.userType = UserType.find(userType);
    }

    public String getUserType(){
        return this.userType.getValue();
    }

}
