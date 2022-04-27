package com.example.library.service.core;

import com.example.library.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getUnBorrowedBookList();
    boolean addBook(Book book);
    List<Book> getBookList();
}
