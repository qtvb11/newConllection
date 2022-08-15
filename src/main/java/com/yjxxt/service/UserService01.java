package com.yjxxt.service;

import com.yjxxt.pojo.User;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 用户记录如何存储???
 *    List-->ArrayList<User>
 *    Map-->HashMap<Integer,User>
 *    Set-->HashSet<User>
 *    List<Map<k,v>>
 */

public class UserService01 {
    private List<User> users;


    public UserService01() {
        users =new ArrayList<User>();
        users.add(new User(1,"admin","123456","admin","",""));
        users.add(new User(2,"test","123456","test","",""));
        users.add(new User(3,"test11","123456789","test11","",""));

    }

    //遍历users
    public void listUsers(){
        users.forEach(user -> System.out.println(user));
    }


    //根据id删除用户

    /**
     * 1,id 非空
     * 2，id 在users中有
     * 3.根据id查询到user,删除
     * @param id
     */
    public void delete(Integer id){
        if (id==null) {
            throw new RuntimeException("id不能为空");
        }
        Integer index=null;
        for (int i = 0; i < users.size(); i++) {
            if(id==users.get(i).getId()){
                index=i;
            }
        }
        if(index==null){
            throw new RuntimeException("用户id不存在");
        }
        users.remove(users.get(index));
        System.out.println("删除用户成功");
    }

    /**添加用户
     *
     * 1,非空校验
     *     id  username nick pwd
     *  2,唯一校验
     *      id username nick
     *  3,执行添加 返回结果
     *
     */
    public void addUser(User user){

        if(user.getId()==null){
            throw new RuntimeException("用户id不能为空");
        }
        if(StringUtils.isBlank(user.getUserName())){
            throw new RuntimeException("用户名不能为空");
        }
        if(StringUtils.isBlank(user.getNick())){
            throw new RuntimeException("昵称不能为空");
        }
        if(StringUtils.isBlank(user.getUserPwd())){
            throw new RuntimeException("密码不能为空");
        }
        long count = users.stream().filter(u -> u.getId().equals(user.getId())).count();
        if(count==1){
            throw new RuntimeException("用户id已经存在");
        }
        count = users.stream().filter(u -> u.getUserName().equals(user.getUserName())).count();
        if(count==1){
            throw new RuntimeException("用户名已经存在");
        }
        count = users.stream().filter(u -> u.getNick().equals(user.getNick())).count();
        if(count==1){
            throw new RuntimeException("昵称已经存在");
        }
        users.add(user);
    }

    /**
     * 1,非空校验
     *      id username pwd nick
     * 2，根据id 查询user
     *      不存在  抛异常
     *      存在  查出user
     * 3，唯一校验
     *      username nick 与非本user的其他用户的username,nick不能相同
     * 4,执行操作
     * @param user
     */
    public void updateUser(User user){
        if(user.getId()==null){
            throw new RuntimeException("用户id不能为空");
        }
        if(StringUtils.isBlank(user.getUserName())){
            throw new RuntimeException("用户名不能为空");
        }
        if(StringUtils.isBlank(user.getNick())){
            throw new RuntimeException("昵称不能为空");
        }
        if(StringUtils.isBlank(user.getUserPwd())){
            throw new RuntimeException("密码不能为空");
        }

        User user1=selectUserById(user.getId());
        if(user1==null){
            throw new RuntimeException("用户id不存在");
        }

        long count = users.stream().filter(u -> u.getUserName().equals(user.getUserName()))
                .filter(u -> !(u.getId().equals(user.getId()))).count();
        if (count==1) {
            throw new RuntimeException("用户名已经存在");
        }
        count = users.stream().filter(u -> u.getNick().equals(user.getNick()))
                .filter(u -> !(u.getId().equals(user.getId()))).count();
        if (count==1) {
            throw new RuntimeException("昵称已经存在");
        }
        users.set(users.indexOf(user1),user);
        System.out.println("用户修改成功");

    }

    private User selectUserById(Integer id) {
        Optional<User> optionalUser = users.stream().filter(u -> id == u.getId()).findFirst();
        return optionalUser.isPresent()?optionalUser.get():null;
    }

    /**
     * 1,非空校验
     *      username pwd
     * 2,根据用户名查询user
     *      user  不存在 抛异常
     *           存在
     *              再取出userPwd 与pwd 比较
     *              不相等  抛异常
     *              相等   登陆成功
     * @param username
     * @param pwd
     */
    public void login(String username,String pwd){
        if(StringUtils.isBlank(username)){
            throw new RuntimeException("用户名不能为空");
        }
        if(StringUtils.isBlank(pwd)){
            throw new RuntimeException("密码不能为空");
        }
        Optional<User> optionalUser = users.stream().filter(u -> u.getUserName().equals(username)).findFirst();
        User user=optionalUser.isPresent()?optionalUser.get():null;
        if(user==null){
            throw new RuntimeException("用户名不存在");
        }
        if(!user.getUserPwd().equals(pwd)){
            throw new RuntimeException("用户密码不正确");
        }
        System.out.println("登陆成功");
    }

}
