package com.example.library.service;

import jakarta.servlet.http.HttpSession;

public interface LoginService {
    boolean login(String username, String password, String rememberMe, HttpSession session);
}
