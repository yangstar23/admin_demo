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
@RequestMapping
@RestController

public class AdminDemoApplication {

    @Autowired
    private UserDao userDao;

    public static void main(String[] args) {
        SpringApplication.run(AdminDemoApplication.class, args);


    }

    //把user对象返回成了json对象
    @GetMapping("/user")
    public List<User> findAll(){
        return userDao.findAll();
    }

    @GetMapping("/user/{id}")
    public User getById(@PathVariable Integer id){
        return userDao.getById(id);
    }

    @GetMapping("/findbyuser")
    public User findByUser(String username,String password){
        return userDao.getByUser(username,password);
    }

    /*新增*/
    @PostMapping("/add")
    public void save(@RequestBody User user){
        if(user.getUsername() ==null|| user.getPassword()==null)
            throw new RuntimeException("参数错误");
        userDao.insert(user);
    }

    //更新
    @PutMapping("/update")
    public String updateUser(@RequestBody User user){
        userDao.update(user);
        return "sucess!";
    }


    //删除
    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable Integer id){
        userDao.deleteById(id);
        return "sucess!";
    }
}
