package com.example.library.dao;

import com.example.library.entity.Borrow;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CoreDao {
    @Select("select br.id, br.bid, br.sid, br.borrow_time, b.name book_name, s.name student_name " +
            "from borrow br inner join book b on br.bid = b.bid inner join student s on br.sid = s.sid " +
            "order by br.borrow_time desc")
    @Results({
            @Result(column = "bid", property = "bookId"),
            @Result(column = "sid", property = "studentId"),
            @Result(column = "borrow_time", property = "borrowTime"),
            @Result(column = "book_name", property = "bookName"),
            @Result(column = "student_name", property = "studentName")
    })
    List<Borrow> getBorrowList();

    @Insert("insert into borrow (sid, bid) values (#{studentId}, #{bookId})")
    int insertBorrow(@Param("bookId") Integer bookId, @Param("studentId") Integer studentId);
}
