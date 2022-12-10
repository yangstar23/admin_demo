package com.example.admin_demo.controller;

import com.example.admin_demo.AdminDemoApplication;
import com.example.admin_demo.dao.UserDao;
import com.example.admin_demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping()
public class UserController{
    @Autowired
    private UserDao userDao;

    //把user对象返回成了json对象
    /*查找所有用户*/
    @GetMapping("/user")
    public List<User> findAll(){
        return userDao.findAll();
    }
    /*按照id查找*/
    @GetMapping("/user/{id}")
    public User getById(@PathVariable Integer id){
        return userDao.getById(id);
    }
    /*找到用户名是xxx,而且密码是xxx的用户*/
    @GetMapping("/findbyuser")
    public User findByUser(String username,String password){
        return userDao.getByUser(username,password);
    }
    /*新增用户*/
    @PostMapping("/add")
    public void save(@RequestBody User user){
        if(user.getUsername() ==null|| user.getPassword()==null)
            throw new RuntimeException("参数错误");
        userDao.insert(user);
    }
    /*更新用户信息*/
    @PutMapping("/update")
    public String updateUser(@RequestBody User user){
        userDao.update(user);
        return "sucess!";
    }

    /*按照id删除用户*/
    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable Integer id){
        userDao.deleteById(id);
        return "sucess!";
    }

    /*登录接口*/
    @PostMapping("/login")
    public User login(@RequestBody User user){
        if (user.getUsername()==null || user.getPassword()==null){
            throw new RuntimeException("参数错误");
        }
        return userDao.getByUser(user.getUsername(),user.getPassword());
    }

}
