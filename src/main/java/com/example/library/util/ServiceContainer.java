package com.example.library.util;

import com.example.library.service.auth.AuthService;
import com.example.library.service.auth.impl.AuthServiceImpl;
import com.example.library.service.core.BookService;
import com.example.library.service.core.BorrowService;
import com.example.library.service.core.StudentService;
import com.example.library.service.core.impl.BookServiceImpl;
import com.example.library.service.core.impl.BorrowServiceImpl;
import com.example.library.service.core.impl.StudentServiceImpl;

/**
 * @author fanzhen
 * @version 1.0
 * @date 2022/4/25
 */
public class ServiceContainer {
    private static final AuthService authService;
    private static final BorrowService borrowService;
    private static final BookService bookService;
    private static final StudentService studentService;

    static {
        authService = new AuthServiceImpl();
        borrowService = new BorrowServiceImpl();
        bookService = new BookServiceImpl();
        studentService = new StudentServiceImpl();
    }

    public static AuthService getAuthService() {
        return authService;
    }

    public static BorrowService getBorrowService() {
        return borrowService;
    }

    public static BookService getBookService() {
        return bookService;
    }

    public static StudentService getStudentService() {
        return studentService;
    }
}
