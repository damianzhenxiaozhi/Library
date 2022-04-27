package com.example.library.service.auth.impl;

import com.example.library.dao.UserDao;
import com.example.library.entity.User;
import com.example.library.service.auth.AuthService;
import com.example.library.util.MybatisUtil;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;

/**
 * @author fanzhen
 * @version 1.0
 * @date 2022/4/25
 */
public class AuthServiceImpl implements AuthService {
    @Override
    public boolean login(String username, String password, HttpSession session) {
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

    @Override
    public void logout(HttpSession session) {
        session.removeAttribute("user");
    }

}
