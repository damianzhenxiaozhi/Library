package com.example.library.dao;

import com.example.library.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface UserDao {
    @Select("select * from user where username = #{username} and password = #{password}")
//    @Results({
//            @Result(column = "username", property = "username"),
//            @Result(column = "password", property = "password")
//    })
    User getUser(@Param("username") String username, @Param("password") String password);
}
