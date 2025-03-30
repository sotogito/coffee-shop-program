package com.coffeeshop.user.service;

import com.coffeeshop.common.MyBatisSessionFactory;
import com.coffeeshop.user.model.dao.UserMapper;
import com.coffeeshop.user.model.dto.User;
import org.apache.ibatis.exceptions.PersistenceException;
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

    public User join(User user) {
        try(SqlSession sqlSession = INSTANCE.getSqlSession()){ ///끝나면 자동으로 close함
            userMapper = sqlSession.getMapper(UserMapper.class);

            int userJoinResult = 0;
            int userTypeJoinResult = 0;
            try {
                userJoinResult = userMapper.join(user);
                userTypeJoinResult = userMapper.joinUserType(user);
            } catch (PersistenceException e) {
                throw new IllegalArgumentException("이미 존재하는 id 입니다.");
            }

            if(userJoinResult != 1 || userTypeJoinResult != 1){
                sqlSession.rollback();
                throw new IllegalArgumentException("회원가입에 실패하였습니다.");
            }

            sqlSession.commit();
            return user;
        }
    }
}
