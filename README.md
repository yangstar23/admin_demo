# 前后端分离的管理系统
# 前后端分离的管理系统

技术栈:技术栈：Springboot、Mybatis-Plus、Mysql、Vue全家桶、Vite、Axios、Element-Plus

实体类
```java
@Data  
public class User {  
    private String username;  
    private String password;  
}
```

```java
注解
@RequestMapping //定义路由 
@RestController //包含@Controller和@ResponseBody,是一个组合注解,专门用来开发Restful接口
@GetMapping("/{msg}")
@PathVariable
```

postman的使用
> 请求了一个get
![[Pasted image 20221129174105.png]]
```java
@GetMapping("/{msg}")  
public String hello(@PathVariable String msg){  
   return "你好,我是:"+msg;  
}
```

```java
@GetMapping("/{msg}")  
public User hello(@PathVariable String msg){  
    User user=new User();  
    user.setUsername(msg);  
    return user;  
}

```
![[Pasted image 20221129174318.png]]
返回一个json对象





## 数据库单表的CRUD

返回的是数组对象用一个[]框住

![image-20221129175134859](/Users/yangstar/Library/Application Support/typora-user-images/image-20221129175134859.png)

**@PathVarible**用来接收输入

<img src="/Users/yangstar/Library/Application Support/typora-user-images/image-20221129175207979.png" alt="image-20221129175207979" style="zoom:33%;" />



### 数据库创建

![image-20221129175701894](/Users/yangstar/Library/Application Support/typora-user-images/image-20221129175701894.png)



application.properties文件配置

```java


spring.application.name=springboot

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.datasource.url=jdbc:mysql://localhost:3306/adminDemo?serverTimezone=GMT%2b8&characterEncoding=utf8

spring.datasource.username=root
spring.datasource.password=m4a11234

server.port=9091

mybatis.mapper-locations=classpath:mappers/*xml

mybatis.type-aliases-package=com.example.springboot.entity


```

<img src="/Users/yangstar/Library/Application Support/typora-user-images/image-20221129180045796.png" alt="image-20221129180045796" style="zoom:200%;" />

> 建立数据表的时候,记得写注释,告诉别人这个字段是干什么的





写点简单的sql语句

#### 查询所有用户

> SELECT *from `user`

![image-20221129180907345](/Users/yangstar/Library/Application Support/typora-user-images/image-20221129180907345.png)



#### 增加用户

![image-20221129180728558](/Users/yangstar/Library/Application Support/typora-user-images/image-20221129180728558.png)



#### 更新用户密码

![image-20221129180945070](/Users/yangstar/Library/Application Support/typora-user-images/image-20221129180945070.png)

修改成功:

![image-20221129181005266](/Users/yangstar/Library/Application Support/typora-user-images/image-20221129181005266.png)



#### 删除用户

![image-20221129181202586](/Users/yangstar/Library/Application Support/typora-user-images/image-20221129181202586.png)

再次执行发现已经没有了id=1的用户了

![image-20221129181248133](/Users/yangstar/Library/Application Support/typora-user-images/image-20221129181248133.png)





#### mybatis的配置

UserMapper.xml文件

![image-20221129191916438](/Users/yangstar/Library/Application Support/typora-user-images/image-20221129191916438.png)

![image-20221129192109115](/Users/yangstar/Library/Application Support/typora-user-images/image-20221129192109115.png)

跳转的小鸟,路径正确就会自己跳转,

还可以帮你创建标签:

![image-20221129192355237](/Users/yangstar/Library/Application Support/typora-user-images/image-20221129192355237.png)

![image-20221129192406987](/Users/yangstar/Library/Application Support/typora-user-images/image-20221129192406987.png)





接口开发



#### 常见错误

![image-20221129193921422](/Users/yangstar/Library/Application Support/typora-user-images/image-20221129193921422.png)

执行之后报错:

Field userDao in com.example.admin_demo.AdminDemoApplication required a bean of type 'com.example.admin_demo.dao.UserDao' that could not be found.

##### 解决方法

![image-20221129193848548](/Users/yangstar/Library/Application Support/typora-user-images/image-20221129193848548.png)

加上mybatis的注解@Mapper





#### 用List不用User的原因

> 查询多个要用LIst接收,不能返回对象
>
> 不知道要返回几个,那么就用LIst接收

正常情况:

![image-20221129194622529](/Users/yangstar/Library/Application Support/typora-user-images/image-20221129194622529.png)

返回数组:

![image-20221129194634414](/Users/yangstar/Library/Application Support/typora-user-images/image-20221129194634414.png)



如果返回User的话,只有一行数据还好,如果数据多了就会这样子报错

> 后台错误

![image-20221129194655046](/Users/yangstar/Library/Application Support/typora-user-images/image-20221129194655046.png)

> 翻译:太多返回结果异常,期待返回一个,结果return了2个

<img src="/Users/yangstar/Library/Application Support/typora-user-images/image-20221129194805734.png" alt="image-20221129194805734" style="zoom:150%;" />





#### 按照id查找

- sql语句

![image-20221129195654891](/Users/yangstar/Library/Application Support/typora-user-images/image-20221129195654891.png)

- controller

  ![image-20221129200831736](/Users/yangstar/Library/Application Support/typora-user-images/image-20221129200831736.png)



问题:

> 同一个地址有两个接口

![image-20221130085659842](/Users/yangstar/Library/Application Support/typora-user-images/image-20221130085659842.png)



> 框架无法识别username,不够智能

![image-20221130085842843](/Users/yangstar/Library/Application Support/typora-user-images/image-20221130085842843.png)



![image-20221130091018619](/Users/yangstar/Library/Application Support/typora-user-images/image-20221130091018619.png)



debug

![image-20221130090614894](/Users/yangstar/Library/Application Support/typora-user-images/image-20221130090614894.png)





post请求

>  前端传来一个json,后端用一个对象接收(RequestBody)



> controller

![image-20221130092535770](/Users/yangstar/Library/Application Support/typora-user-images/image-20221130092535770.png)

> dao

![image-20221130092615259](/Users/yangstar/Library/Application Support/typora-user-images/image-20221130092615259.png)

> sql语句

![image-20221130092628876](/Users/yangstar/Library/Application Support/typora-user-images/image-20221130092628876.png)

> postman请求

![image-20221130092713229](/Users/yangstar/Library/Application Support/typora-user-images/image-20221130092713229.png)

> 数据库设计
>
> 用户名只能是唯一的

![image-20221130093643294](/Users/yangstar/Library/Application Support/typora-user-images/image-20221130093643294.png)

这样操作之后,插入一样的用户名就会报错

![image-20221130093749583](/Users/yangstar/Library/Application Support/typora-user-images/image-20221130093749583.png)

报错:唯一键冲突

va.sql.SQLIntegrityConstraintViolationException: Duplicate entry 'yjx' for key 'user.uni_username'



#### 资源区分:

1. url路径
2. 请求方式,get,put....





### 更新用户名密码

@PutMapping

![image-20221130100850558](/Users/yangstar/Library/Application Support/typora-user-images/image-20221130100850558.png)

![image-20221130100902529](https://raw.githubusercontent.com/yangstar23/picgo/main/img/image-20221130100902529.png)

![image-20221130100930723](/Users/yangstar/Library/Application Support/typora-user-images/image-20221130100930723.png)

![image-20221130100953341](/Users/yangstar/Library/Application Support/typora-user-images/image-20221130100953341.png)

