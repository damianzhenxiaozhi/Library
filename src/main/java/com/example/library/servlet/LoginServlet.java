package com.example.library.servlet;

import com.example.library.service.LoginService;
import com.example.library.util.ServiceContainer;
import com.example.library.util.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private LoginService loginService;

    @Override
    public void init() throws ServletException {
        loginService = ServiceContainer.getLoginService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getSession().getAttribute("user") != null) {
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

        HttpSession session = req.getSession();
        if (loginService.login(username, password, rememberMe, session)) {
            resp.sendRedirect("index");
            return;
        }

        session.setAttribute("loginFailure", true);
        doGet(req, resp);
    }
}
