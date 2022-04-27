package com.example.library.service.core.impl;

import com.example.library.dao.CoreDao;
import com.example.library.entity.Student;
import com.example.library.service.core.StudentService;
import com.example.library.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    @Override
    public List<Student> getStudentList() {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            CoreDao coreDao = sqlSession.getMapper(CoreDao.class);
            return coreDao.getStudentList();
        }
    }
}
