package com.coffeeshop.common;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public enum MyBatisSessionFactory {
    INSTANCE;

    private final SqlSessionFactory sqlSessionFactory;

    MyBatisSessionFactory() {
        try{
            InputStream inputStream = Resources.getResourceAsStream("config/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public SqlSession getSqlSession() {
        return sqlSessionFactory.openSession(false);
    }

}
