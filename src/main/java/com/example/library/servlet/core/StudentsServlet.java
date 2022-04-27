package com.example.library.servlet.core;

import com.example.library.entity.Student;
import com.example.library.entity.User;
import com.example.library.service.core.BookService;
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

@WebServlet("/students")
public class StudentsServlet extends HttpServlet {
    private StudentService studentService;

    @Override
    public void init() throws ServletException {
        studentService = ServiceContainer.getStudentService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        User user = (User) req.getSession().getAttribute("user");
        context.setVariable("nickname", user.getNickname());

        List<Student> studentList = studentService.getStudentList();
        context.setVariable("studentList", studentList);

        ThymeleafUtil.process("students.html", context, resp.getWriter());
    }
}
