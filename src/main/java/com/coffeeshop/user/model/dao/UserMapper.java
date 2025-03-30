package com.coffeeshop.user.model.dao;

import com.coffeeshop.user.model.dto.User;

public interface UserMapper {
    User login(User user);
}
