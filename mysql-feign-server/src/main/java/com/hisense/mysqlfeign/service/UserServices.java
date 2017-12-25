package com.hisense.mysqlfeign.service;

import com.hisense.mysqlfeign.entity.User;
import feign.MysqlFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServices {

    public String show(){
        return "hello";
    }
    @Autowired
    MysqlFeignClient mysqlFeignClient;


//    @Transactional(rollbackFor = {IllegalArgumentException.class})
    @Transactional()
    public void add(User user){

       mysqlFeignClient.add(user);
    }

    public void delete(String userId,String userCode){
        mysqlFeignClient.delete(userId,userCode);
    }

    public void update(User  user){
        mysqlFeignClient.update(user);
    }

    public void detail(String userId, String userCode){
        mysqlFeignClient.detail(userId,userCode);
    }

    public void list(){
        mysqlFeignClient.list();
    }

    public void getInfos(String select,String parameter, int pageNum, int pageSize) {
        mysqlFeignClient.getInfos(select,parameter,pageNum,pageSize);
    }
}
