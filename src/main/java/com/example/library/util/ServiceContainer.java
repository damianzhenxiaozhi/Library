package com.example.library.util;

import com.example.library.service.auth.AuthService;
import com.example.library.service.auth.impl.AuthServiceImpl;
import com.example.library.service.core.BorrowService;
import com.example.library.service.core.impl.BorrowServiceImpl;

/**
 * @author fanzhen
 * @version 1.0
 * @date 2022/4/25
 */
public class ServiceContainer {
    private static final AuthService authService;
    private static final BorrowService borrowService;

    static {
        authService = new AuthServiceImpl();
        borrowService = new BorrowServiceImpl();
    }

    public static AuthService getAuthService() {
        return authService;
    }

    public static BorrowService getBorrowService() {
        return borrowService;
    }
}
