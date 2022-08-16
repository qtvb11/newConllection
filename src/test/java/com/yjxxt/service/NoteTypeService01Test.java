package com.yjxxt.service;

import com.yjxxt.pojo.NoteType;
import org.junit.Before;
import org.junit.Test;

public class NoteTypeService01Test {
    private NoteTypeService01 noteTypeService01=null;


    @Before
    public void init(){
        System.out.println("测试方法执行前执行.......");
        noteTypeService01=new NoteTypeService01();
    }


    //根据登录用户查询云记类别
    @Test
    public void listNoteType(){
        noteTypeService01.listNoteType(2);
    }


    //添加noteType
    @Test
    public void listAllNOteTypeMap(){
        noteTypeService01.addNoteType(new NoteType(8,"javac",1));
    }



}
