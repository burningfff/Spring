package com.hisense.sql.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hisense.sql.dao.UserDao;
import com.hisense.sql.entity.JsonResult;
import com.hisense.sql.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServices {
    @Autowired
    private UserDao userDao;

    public String show(){
        return "hello world!";
    }

    public List<User> showDao(int age){
        return userDao.get(age);
    }

//    @Transactional(rollbackFor = {IllegalArgumentException.class})
    @Transactional()

    public JsonResult add(User user){

        System.out.println("添加开始");
        User User= userDao.getUserByCode(user.getUserCode());
        if(User!=null){
            return new JsonResult("userCode已存在",false);
        }
        user.setUserId(UUID.randomUUID().toString());
        user.setCreateTime(new Date());
        user.setModifiedTime(new Date());
        userDao.insertUser(user);
        System.out.println("添加成功");
        return new JsonResult("success",true);
    }

    public JsonResult delete(String userId,String userCode){
        User User=null;
        if(userId==null&&userCode==null){
            return new JsonResult("缺少参数userId或userCode",false);
        }else if(userId==null){
            User= userDao.getUserByCode(userCode);
            if(User==null){
                return new JsonResult("userCode不存在,无法删除",false);
            }
            userDao.deletdUserByCode(userCode);
        }else {
            User= userDao.getUserById(userId);
            if(User==null){
                return new JsonResult("userId不存在,无法删除",false);
            }
            userDao.deletdUserById(userId);
        }

        return new JsonResult("success",true);
    }

    public JsonResult update(User  user){
        User User= userDao.getUserById(user.getUserId());
        if(User==null){
            return new JsonResult("没有此userId",false);
        }

        user.setModifiedTime(new Date());
        userDao.updateUser(user);
        return  new JsonResult("success",true);
    }

    public JsonResult detail(String userId, String userCode){
        boolean status=true;
        String message="";
        User User=null;
        if(userId==null){
            User= userDao.getUserByCode(userCode);
            if(User==null){
                status=false;
                message="userCode不存在！";
            }
        }else if(userCode==null){
            User= userDao.getUserById(userId);
            if(User==null){
                status=false;
                message="userId不存在！";
            }
        }

        return  new JsonResult(message,status,User);
    }

    public JsonResult list(int pageNum,int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        Page<User> list= userDao.getUserList();
        //把list包装成PageInfo对象才能序列化,该插件也默认实现了一个PageInfo
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        //得到总的数据条数
        Long total=pageInfo.getTotal();
        return  new JsonResult(total,"success",true,list);
    }

    public JsonResult getInfos(String select,String parameter, int pageNum, int pageSize) {
        Page<User> list=null;
        Long total=null;
//        System.out.print(select+"+++"+select.getClass().toString());
//        System.out.print(parameter+"+++"+parameter.getClass().toString());
        if(select.equals("1")){
            PageHelper.startPage(pageNum, pageSize);
            list= userDao.getInfosByuserCode(parameter);
            //把list包装成PageInfo对象才能序列化,该插件也默认实现了一个PageInfo
            PageInfo<User> pageInfo = new PageInfo<User>(list);
            //得到总的数据条数
            total=pageInfo.getTotal();
        }else if(select.equals("2")){
            Integer age=Integer.valueOf(parameter);
            PageHelper.startPage(pageNum, pageSize);
            list= userDao.getInfosByage(age);
            PageInfo<User> pageInfo = new PageInfo<User>(list);
            //得到总的数据条数
            total=pageInfo.getTotal();
        }else if(select.equals("3")){
            PageHelper.startPage(pageNum, pageSize);
            list= userDao.getInfosBymail(parameter);
            PageInfo<User> pageInfo = new PageInfo<User>(list);
            //得到总的数据条数
            total=pageInfo.getTotal();
        }else if(select.equals("4")){
            PageHelper.startPage(pageNum, pageSize);
            list= userDao.getInfosByphone(parameter);
            PageInfo<User> pageInfo = new PageInfo<User>(list);
            //得到总的数据条数
            total=pageInfo.getTotal();
        }else {
            PageHelper.startPage(pageNum, pageSize);
            list= userDao.getInfos(parameter);
            PageInfo<User> pageInfo = new PageInfo<User>(list);
            //得到总的数据条数
            total=pageInfo.getTotal();
        }
        return  new JsonResult(total,"success",true,list);
    }
}
