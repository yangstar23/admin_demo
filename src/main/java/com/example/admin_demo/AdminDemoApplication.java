package com.example.admin_demo;

import com.example.admin_demo.dao.UserDao;
import com.example.admin_demo.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
public class AdminDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminDemoApplication.class, args);
    }


}


