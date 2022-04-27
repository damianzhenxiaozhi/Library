package com.example.library.dao;

import com.example.library.entity.Book;
import com.example.library.entity.Borrow;
import com.example.library.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CoreDao {
    @Select("select br.id, br.bid, br.sid, br.borrow_time, b.title book_title, s.name student_name " +
            "from borrow br inner join book b on br.bid = b.bid inner join student s on br.sid = s.sid " +
            "order by br.borrow_time desc")
    @Results({
            @Result(column = "bid", property = "bookId"),
            @Result(column = "sid", property = "studentId"),
            @Result(column = "borrow_time", property = "borrowTime"),
            @Result(column = "book_title", property = "bookTitle"),
            @Result(column = "student_name", property = "studentName")
    })
    List<Borrow> getBorrowList();

    @Insert("insert into borrow (sid, bid, borrow_time) values (#{studentId}, #{bookId}, NOW())")
    int insertBorrow(@Param("studentId") Integer studentId, @Param("bookId") Integer bookId);

    @Delete("delete from borrow where id = #{id}")
    int deleteBorrow(Integer id);

    @Select("select * from book order by bid")
    List<Book> getBookList();

    @Insert("insert into book (title, price, `desc`) values (#{title}, #{price}, #{desc})")
    int insertBook(Book book);

    @Select("select book.bid, book.desc, book.title, book.price from book " +
            "left join borrow b on book.bid = b.bid where b.sid is null order by book.bid")
    List<Book> getUnBorrowedBookList();

    @Select("select * from student")
    List<Student> getStudentList();
}
