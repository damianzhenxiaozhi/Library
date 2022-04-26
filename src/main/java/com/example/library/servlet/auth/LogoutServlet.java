package com.example.library.servlet.auth;

import com.example.library.service.auth.AuthService;
import com.example.library.util.ServiceContainer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private AuthService authService;

    @Override
    public void init() throws ServletException {
        authService = ServiceContainer.getAuthService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authService.logout(req.getSession());
        resp.sendRedirect("login");
    }
}
