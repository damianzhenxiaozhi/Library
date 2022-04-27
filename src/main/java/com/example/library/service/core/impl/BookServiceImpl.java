package com.example.library.service.core.impl;

import com.example.library.dao.CoreDao;
import com.example.library.entity.Book;
import com.example.library.entity.Borrow;
import com.example.library.entity.User;
import com.example.library.service.core.BookService;
import com.example.library.util.MybatisUtil;
import com.example.library.util.ThymeleafUtil;
import org.apache.ibatis.session.SqlSession;
import org.thymeleaf.context.Context;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookServiceImpl implements BookService {
    @Override
    public List<Book> getUnBorrowedBookList() {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            CoreDao coreDao = sqlSession.getMapper(CoreDao.class);
            return coreDao.getUnBorrowedBookList();
        }
    }

    @Override
    public boolean addBook(Book book) {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            CoreDao coreDao = sqlSession.getMapper(CoreDao.class);
            int count = coreDao.insertBook(book);
            if (count == 1) {
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public List<Book> getBookList() {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            CoreDao coreDao = sqlSession.getMapper(CoreDao.class);
            List<Book> bookList = coreDao.getBookList();
            List<Borrow> borrowList = coreDao.getBorrowList();
            Set<Integer> borrowedBookIds = new HashSet<>();
            borrowList.forEach(e -> borrowedBookIds.add(e.getBookId()));
            bookList.forEach(e -> e.setBorrow(borrowedBookIds.contains(e.getBid())));
            return bookList;
        }
    }
}
