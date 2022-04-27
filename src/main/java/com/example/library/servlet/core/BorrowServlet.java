package com.example.library.servlet.core;

import com.example.library.entity.Book;
import com.example.library.entity.Student;
import com.example.library.entity.User;
import com.example.library.service.core.BookService;
import com.example.library.service.core.BorrowService;
import com.example.library.service.core.StudentService;
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

@WebServlet("/add-borrow")
public class BorrowServlet extends HttpServlet {
    private BorrowService borrowService;
    private StudentService studentService;
    private BookService bookService;

    @Override
    public void init() throws ServletException {
        borrowService = ServiceContainer.getBorrowService();
        studentService = ServiceContainer.getStudentService();
        bookService = ServiceContainer.getBookService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        User user = (User) req.getSession().getAttribute("user");
        context.setVariable("nickname", user.getNickname());

        List<Book> bookList = bookService.getUnBorrowedBookList();
        context.setVariable("bookList", bookList);

        List<Student> studentList = studentService.getStudentList();
        context.setVariable("studentList", studentList);

        ThymeleafUtil.process("add-borrow.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int studentId = Integer.parseInt(req.getParameter("studentId"));
        int bookId = Integer.parseInt(req.getParameter("bookId"));
        borrowService.addBorrow(studentId, bookId);
        resp.sendRedirect("index");
    }
}
