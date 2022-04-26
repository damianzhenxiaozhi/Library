package com.example.library.service.core;

import com.example.library.entity.Borrow;

import java.util.List;

/**
 * @author fanzhen
 * @version 1.0
 * @date 2022/4/25
 */
public interface BorrowService {
    List<Borrow> getBorrowList();
    boolean addBorrow(Integer bookId, Integer studentId);
}
