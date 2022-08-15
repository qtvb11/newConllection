package com.yjxxt.service;

import com.yjxxt.pojo.User;
import org.junit.Before;
import org.junit.Test;

public class UserServiceTest01 {
    private UserService01 userService01=null;
    @Before
    public void init(){
        userService01=new UserService01();
        System.out.println("在测试方法前执行，初始化执行空构造，给users中添加数据");
    }
    @Test
    public void del(){
        userService01.listUsers();
        userService01.delete(3);
        userService01.listUsers();
    }

    @Test
    public void addUser(){
        userService01.listUsers();
        System.out.println("测试后----------");
        userService01.addUser(new User(4,"test456","456","test9","",""));
        userService01.listUsers();
    }

    @Test
    public void updateuser(){
        userService01.listUsers();
        System.out.println("测试后");
        userService01.updateUser(new User(2,"test","123456","test788","",""));
        userService01.listUsers();
    }

    @Test
    public void login(){
        userService01.login("admin","123456");
    }

}
