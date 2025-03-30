package com.coffeeshop.user.service;

import com.coffeeshop.common.MyBatisSessionFactory;
import com.coffeeshop.user.model.dao.UserMapper;
import com.coffeeshop.user.model.dto.User;
import org.apache.ibatis.session.SqlSession;
import static com.coffeeshop.common.MyBatisSessionFactory.INSTANCE;


public class LoginService {
    private UserMapper userMapper;

    public User login(User user) {
        SqlSession sqlSession = INSTANCE.getSqlSession();

        userMapper = sqlSession.getMapper(UserMapper.class);
        User result = userMapper.login(user);

        if(result == null){
            throw new IllegalArgumentException("아이디 비밀번호를 다시 입력해주세요.");
        }

        sqlSession.close();
        return result;
    }

}
