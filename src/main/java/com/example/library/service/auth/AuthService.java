package com.example.library.service.auth;

import jakarta.servlet.http.HttpSession;

public interface AuthService {
    boolean login(String username, String password, String rememberMe, HttpSession session);

    void logout(HttpSession session);
}
