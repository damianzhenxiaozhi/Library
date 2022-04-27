package com.example.library.servlet.auth;

import com.example.library.service.auth.AuthService;
import com.example.library.util.ServiceContainer;
import com.example.library.util.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private AuthService authService;

    @Override
    public void init() throws ServletException {
        authService = ServiceContainer.getAuthService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getSession().getAttribute("user") != null) {
            resp.sendRedirect("index");
            return;
        }

        String username = null;
        String password = null;
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                username = cookie.getValue();
            } else if (cookie.getName().equals("password")) {
                password = cookie.getValue();
            }
        }
        if (authService.login(username, password, req.getSession())) {
            resp.sendRedirect("index");
            return;
        }


        Context context = new Context();
        if (req.getSession().getAttribute("loginFailure") != null) {
            context.setVariable("loginFail", true);
            req.getSession().removeAttribute("loginFailure");
        }

        ThymeleafUtil.process("login.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String rememberMe = req.getParameter("rememberMe");

        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                username = cookie.getValue();
            } else if (cookie.getName().equals("password")) {
                password = cookie.getValue();
            }
        }

        HttpSession session = req.getSession();
        if (authService.login(username, password, session)) {
            if (rememberMe != null) {
                Cookie usernameCookie = new Cookie("username", username);
                usernameCookie.setMaxAge(60 * 60 * 24 * 7);
                Cookie passwordCookie = new Cookie("password", password);
                usernameCookie.setMaxAge(60 * 60 * 24 * 7);
                resp.addCookie(usernameCookie);
                resp.addCookie(passwordCookie);
            }
            resp.sendRedirect("index");
            return;
        }

        Cookie usernameCookie = new Cookie("username", "");
        usernameCookie.setMaxAge(0);
        Cookie passwordCookie = new Cookie("password", "");
        passwordCookie.setMaxAge(0);
        resp.addCookie(usernameCookie);
        resp.addCookie(passwordCookie);

        session.setAttribute("loginFailure", true);
        doGet(req, resp);
    }
}
