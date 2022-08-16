package com.yjxxt.service;


import com.yjxxt.pojo.NoteType;
import com.yjxxt.pojo.User;
import org.apache.commons.lang3.StringUtils;

import java.util.*;


/**
 * 云记类别管理  用Map
 *    云记类别遍历
 *    云记类别添加
 *    云记类别更新
 *    云记类别删除
 */
public class NoteTypeService01 {

    private List<User> users;
    private Map<Integer,NoteType> noteTypeMap;

    private List<Map<String,Object>> noteList=null;

    private Set<Map.Entry<Integer, NoteType>> entries=null;

    public NoteTypeService01() {
        noteTypeMap= new HashMap<Integer, NoteType>();
        noteTypeMap.put(1,new NoteType(1,"java",1));
        noteTypeMap.put(2,new NoteType(2,"php",1));
        noteTypeMap.put(3,new NoteType(3,"scala",2));

        users =new ArrayList<User>();
        users.add(new User(1,"admin","123456","admin","",""));
        users.add(new User(2,"test","123456","test","",""));

//        noteList=new List<Map<String, Object>>() {
//        }
    }

    //遍历noteTypeMap
    public void listAllTypeMap(){
        entries = noteTypeMap.entrySet();
        for (Map.Entry<Integer, NoteType> entry:entries) {
            System.out.println(entry.getKey()+"--->"+entry.getValue());
        }
    }


    //添加noteType
    public void addNoteType(NoteType noteType){
        /**
         * 0，noteTypeId 非空，且唯一
         * 1.参数校验
         *    id 用户id 类别名 不能为空 用户id 必须存在(UserService->List<User> 必须存在对应用户记录)
         * 2.当前用户下类别名称不可重复
         * 3.执行添加
         */
        if(noteType.getId()==null){
            throw new RuntimeException("id非空");
        }
        if(noteType.getUserId()==null){
            throw new RuntimeException("userId非空");
        }
        if(StringUtils.isBlank(noteType.getTypeName())){
            throw new RuntimeException("类别名不能为空");
        }
        Set<Integer> set = noteTypeMap.keySet();
        if(set.contains(noteType.getId())){
            throw new RuntimeException("id已经存在");
        }
        Optional<User> optionalUser = users.stream().filter(u -> u.getId().equals(noteType.getUserId())).findFirst();
        User user=optionalUser.isPresent()?optionalUser.get():null;
        if(user==null){
            throw new RuntimeException("userId不存在");
        }

        List<NoteType> noteTypeList=this.listNoteType(noteType.getUserId());
        long count = noteTypeList.stream().filter(noteType1 -> noteType1.getTypeName().equals(noteType.getTypeName())).count();
        if (count==1) {
            throw new RuntimeException("类别名已经存在");
        }


        noteTypeMap.put(5,noteType);
        System.out.println();


    }

    /**
     * 根据登录用户查询云记类别
     * 1.userid  非空
     *      userid 在user表中存在
     * 2，查询 noteType
     *      返回结果
     * @param userId
     * @return
     */
    public List<NoteType> listNoteType(Integer userId){
        if(!userIdExist(userId)){
            throw new RuntimeException("用户名非法");
        }
        Set<Map.Entry<Integer, NoteType>> entries = noteTypeMap.entrySet();
        for (Map.Entry<Integer, NoteType> entry:entries) {
            if(entry.getValue().getUserId()==userId){
                System.out.println(entry.getValue());
            }
        }
        return null;
    }


    public void updateNoteType(NoteType noteType){
        /**
         * 1.参数校验
         *    类别名 不能为空
         *    用户id 必须存在(UserService->List<User> 必须存在对应用户记录)
         *    云记类别id 必须存在
         * 2.当前用户下类别名称不可重复
         * 3.执行更新
         */

    }

    public void delNoteType(Integer noteTypeId){
        /**
         * 1.参数校验
         *    云记类别id 必须存在
         * 2.如果类别下存在云记记录 不允许删除
         * 3.执行删除
         */
        if (noteTypeId==null) {
            throw new RuntimeException("noteTypeId非空");
        }
        if (!noteTypeMap.keySet().contains(noteTypeId)) {
            throw new RuntimeException("noteTypeId不存在");
        }
//        noteList.stream().filter()

    }


    //封装 userId 非空 存在
    public Boolean userIdExist(Integer userId){
        if(userId==null){
            return false;
        }
        Optional<User> optionalUser = users.stream().filter(u -> u.getId().equals(userId)).findFirst();
        User user=optionalUser.isPresent()?optionalUser.get():null;
        if(user==null){
            return false;
        }
        return true;
    }


}
