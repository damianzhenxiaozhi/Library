package com.example.library.servlet.core;

import com.example.library.entity.Borrow;
import com.example.library.entity.User;
import com.example.library.service.core.BorrowService;
import com.example.library.util.ServiceContainer;
import com.example.library.util.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.List;

/**
 * @author fanzhen
 * @version 1.0
 * @date 2022/4/25
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private BorrowService borrowService;

    @Override
    public void init() throws ServletException {
        borrowService = ServiceContainer.getBorrowService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        User user = (User) req.getSession().getAttribute("user");
        context.setVariable("nickname", user.getNickname());
        List<Borrow> borrowList = borrowService.getBorrowList();
        context.setVariable("borrowList", borrowList);
        ThymeleafUtil.process("index.html", context, resp.getWriter());
    }
}
