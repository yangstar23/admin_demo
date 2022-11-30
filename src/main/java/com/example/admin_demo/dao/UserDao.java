package com.example.admin_demo.dao;

import com.example.admin_demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao  {
    List<User> findAll();

    User getById(Integer id);


    User getByUser(@Param("username") String username, @Param("password") String password);

    //insert 接口返回一个数字,不能返回User对象
    int insert(User user);
}
