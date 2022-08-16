package com.yjxxt.service;

import com.yjxxt.pojo.NoteType;
import org.junit.Before;
import org.junit.Test;

public class NoteTypeServiceTest {

    private NoteTypeService noteTypeService;

    @Before
    public  void init(){
        noteTypeService =new NoteTypeService();
    }


    @Test
    public void listNoteType(){
        noteTypeService.listNoteType(1);
    }

    @Test
    public void addNoteType(){
        noteTypeService.addNoteType(new NoteType(5,"scala",1));
        noteTypeService.listNoteType(1);
    }


    @Test
    public void updateNoteType(){
        noteTypeService.updateNoteType(new NoteType(3,"java",2));
        noteTypeService.listNoteType(1);
    }

    @Test
    public void deleteNoteType(){
        noteTypeService.delNoteType(1);
        noteTypeService.listNoteType(1);
    }
}
