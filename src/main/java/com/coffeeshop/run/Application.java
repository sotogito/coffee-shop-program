package com.coffeeshop.run;

import com.coffeeshop.common.MyBatisSessionFactory;
import com.coffeeshop.user.view.InputView.LoginView;
import com.coffeeshop.view.MainView;
import org.apache.ibatis.session.SqlSession;

public class Application {
    public static void main(String[] args) {
        new MainView().run();
    }
}
