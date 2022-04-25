package com.example.library.filter;

import com.example.library.entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * @author fanzhen
 * @version 1.0
 * @date 2022/4/25
 */
@WebFilter("/*")
public class AuthFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String url = req.getRequestURL().toString();
        if (url.contains("/static/")) {
            chain.doFilter(req, res);
            return;
        }

        if (url.endsWith("/login")) {
            chain.doFilter(req, res);
            return;
        }

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            chain.doFilter(req, res);
            return;
        }

        res.sendRedirect("login");
    }
}
