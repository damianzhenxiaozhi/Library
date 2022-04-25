package com.example.library.util;

import com.example.library.service.LoginService;
import com.example.library.service.impl.LoginServiceImpl;

/**
 * @author fanzhen
 * @version 1.0
 * @date 2022/4/25
 */
public class ServiceContainer {
    private static final LoginService loginService;

    static {
        loginService = new LoginServiceImpl();
    }

    public static LoginService getLoginService() {
        return loginService;
    }
}
