package com.example.library.service.impl;

import com.example.library.dao.UserDao;
import com.example.library.entity.User;
import com.example.library.service.LoginService;
import com.example.library.util.MybatisUtil;
import com.example.library.util.ThymeleafUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.thymeleaf.context.Context;

import java.io.IOException;

/**
 * @author fanzhen
 * @version 1.0
 * @date 2022/4/25
 */
public class LoginServiceImpl implements LoginService {
    @Override
    public boolean login(String username, String password, String rememberMe, HttpSession session) {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            User user = userDao.getUser(username, password);
            if (user == null) {
                return false;
            }

            session.setAttribute("user", user);
            return true;
        }
    }
}
