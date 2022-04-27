package com.example.library.servlet.core;

import com.example.library.service.core.BorrowService;
import com.example.library.util.ServiceContainer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/return-book")
public class ReturnBookServlet extends HttpServlet {
    private BorrowService borrowService;

    @Override
    public void init() throws ServletException {
        borrowService = ServiceContainer.getBorrowService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int borrowId = Integer.parseInt(req.getParameter("id"));
        borrowService.returnBook(borrowId);
        resp.sendRedirect("index");
    }
}
