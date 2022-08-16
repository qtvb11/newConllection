package com.yjxxt.service;


import com.yjxxt.pojo.Note;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 云记管理
 * 云记添加
 * 云记列表展示
 * 云记更新
 * 云记删除
 */
public class NoteService {
    private List<Map<String, Object>> noteList = null;
    private NoteTypeService noteTypeService;


   /* public NoteTypeService getNoteTypeService() {
        return noteTypeService;
    }*/

    public void setNoteTypeService(NoteTypeService noteTypeService) {
        this.noteTypeService = noteTypeService;
    }

    public NoteService() {
        //noteList =new ArrayList<Map<>>()
        noteList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("typeId", 1);
        map.put("title", "hello");
        map.put("pubTime", new Date());
        map.put("content", "hello");
        noteList.add(map);
    }

    public Integer countNoteByNoteTypeId(Integer noteTypeId) {
        Integer count = 0;
        for (Map<String, Object> map : noteList) {
            if (map.get("typeId").equals(noteTypeId)) {
                count = count + 1;
            }
        }
        return count;
    }

    public void addNote(Note note) {
        /**
         * 1.参数校验
         *    标题非空  类别id 对应的类别记录必须存在
         * 2.标题唯一约束
         *    不同用户(根据typeid 查出用户,判断用户是否存在  存在 再根据用户id查出所有的typeid 再根据typeid查出所有 云记标题 )
         *    标题可以重复
         *    （相同用户-->相同类型标题不可重复）
         * 3.执行添加
         */
        if(null==note){
            throw new RuntimeException("云记不存在");
        }
        if (StringUtils.isBlank(note.getTitle())) {
            throw new RuntimeException("标题非空");
        }
        if(note.getTypeId()==null||null==noteTypeService.findUserIdByTypeId(note.getTypeId())){
            throw new RuntimeException("类别记录不存在");
        }
        Integer userId = noteTypeService.findUserIdByTypeId(note.getTypeId());
        List<Integer> typeIds = noteTypeService.findTypeIdsByUserId(userId);
        // 根据类别id 集合查询所有的云记集合  当前类别对应用户所有的云记记录
        List<Note> noteListTemp = this.findNotesByTypeIds(typeIds);
        long result = noteListTemp.stream().filter(n -> n.getTitle().equals(note.getTitle())).count();
        if (result > 0) {
            throw new RuntimeException("云记标题已存在!");
        }

        Map<String, Object> tempMap = new HashMap<>();
        tempMap.put("content", note.getContent());
        tempMap.put("typeId", note.getTypeId());
        tempMap.put("pubTime", new Date());
        tempMap.put("title", note.getTitle());
        noteList.add(tempMap);

    }

    /**
     * 根据类别id 集合查询所有云记记录
     *
     * @param typeIds
     * @return
     */
    private List<Note> findNotesByTypeIds(List<Integer> typeIds) {
        List<Note> result = new ArrayList<Note>();
        for (Map<String, Object> map : noteList) {
            Integer tempTypeId = Integer.parseInt(map.get("typeId").toString());
            if (typeIds.contains(tempTypeId)) {
                Note temp = new Note();
                temp.setId(Integer.parseInt(map.get("id").toString()));
                temp.setContent((map.get("content").toString()));
                temp.setPubTime(new Date());
                temp.setTitle(map.get("title").toString());
                temp.setTypeId(tempTypeId);
                result.add(temp);
            }
        }
        return result;
    }

    public void listNote() {
        /*noteList.forEach(m -> {
                    m.forEach((k, v) -> {
                        System.out.println(k + "----" + v);
                    });
                    System.out.println("-----------");
                }
        );*/
        for (Map<String, Object> map : noteList) {
            map.forEach((k, v) -> {
                System.out.println(k + "--" + v);
            });
        }
    }


}




