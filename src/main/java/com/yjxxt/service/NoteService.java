package com.yjxxt.service;

import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;

import java.util.*;

public class NoteService {
    private List<Map<String,Object>> noteList=null;
    public NoteService() {
        //noteList =new ArrayList<Map<>>()
        noteList =new ArrayList<Map<String, Object>>();
        Map<String,Object> map=new HashMap<>();
        map.put("id",1);
        map.put("typeId",1);
        map.put("title","hello");
        map.put("pubTime",new Date());
        noteList.add(map);

        Map<String,Object> map1=new HashMap<>();
        map1.put("id",2);
        map1.put("typeId",1);
        map1.put("title","test");
        map1.put("pubTime",new Date());
        noteList.add(map1);
    }

    public Integer countNoteByNoteTypeId(Integer noteTypeId) {
        Integer count =0;
        for (Map<String, Object> map : noteList) {
            if(map.get("typeId").equals(noteTypeId)){
                count=count+1;
            }
        }
        return count;
    }




}
