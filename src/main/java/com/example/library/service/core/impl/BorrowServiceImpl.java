package com.example.library.service.core.impl;

import com.example.library.dao.CoreDao;
import com.example.library.entity.Borrow;
import com.example.library.service.core.BorrowService;
import com.example.library.util.MybatisUtil;
import com.mysql.cj.Session;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class BorrowServiceImpl implements BorrowService {

    @Override
    public List<Borrow> getBorrowList() {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            CoreDao coreDao = sqlSession.getMapper(CoreDao.class);
            return coreDao.getBorrowList();
        }
    }

    @Override
    public boolean addBorrow(Integer bookId, Integer studentId) {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            CoreDao coreDao = sqlSession.getMapper(CoreDao.class);
            int count = coreDao.insertBorrow(bookId, studentId);
            if (count == 1) {
                return true;
            } else {
                return false;
            }
        }
    }
}
