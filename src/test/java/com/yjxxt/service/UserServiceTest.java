package com.yjxxt.service;

import com.yjxxt.pojo.User;
import org.junit.Before;
import org.junit.Test;

public class UserServiceTest {
    private UserService userService=null;


    @Before
    public void init(){
        System.out.println("测试方法执行前执行.......");
        userService=new UserService();
    }

    @Test
    public void addUser(){

        userService.addUser(new User(3,"admin","123456","admin","",""));

    }

    @Test
    public void listUser(){
        userService.listUser();
    }



    @Test
    public void testLogin(){
        userService.login("admin","123456");
    }

    @Test
    public void updateUser(){
        System.out.println("记录更新前.........");
        userService.listUser();
        userService.updateUser(new User(2,"abc","123456","test","test",""));
        System.out.println("记录更新后......");
        userService.listUser();
    }


    @Test
    public void delUser(){
        System.out.println("记录删除前.........");
        userService.listUser();
        userService.delUser(2);
        System.out.println("记录删除后.........");
        userService.listUser();
    }

}
