package com.hisense.sql.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hisense.sql.dao.TestDao;
import com.hisense.sql.domain.JsonResult;
import com.hisense.sql.domain.TestPOJO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TestServices {//这里提供两个方法，一个只是简单返回字符串，另个从mysql数据库里去取出数据显示

    @Autowired
    private TestDao testDao;

    public String show(){
        return "hello world!";
    }

    public List<TestPOJO> showDao(int age){
        return testDao.get(age);
    }

    public JsonResult add(TestPOJO user){
        TestPOJO testPOJO=testDao.getUserByCode(user.getUserCode());
        if(testPOJO!=null){
            return new JsonResult("userCode已存在",false);
        }
        user.setUserId(UUID.randomUUID().toString());
        user.setCreateTime(new Date());
        user.setModifiedTime(new Date());
        testDao.insertUser(user);
        return new JsonResult("success",true);
    }

    public JsonResult delete(String userId,String userCode){
        TestPOJO testPOJO=null;
        if(userId==null&&userCode==null){
            return new JsonResult("缺少参数userId或userCode",false);
        }else if(userId==null){
            testPOJO=testDao.getUserByCode(userCode);
            if(testPOJO==null){
                return new JsonResult("userCode不存在,无法删除",false);
            }
            testDao.deletdUserByCode(userCode);
        }else {
            testPOJO=testDao.getUserById(userId);
            if(testPOJO==null){
                return new JsonResult("userId不存在,无法删除",false);
            }
            testDao.deletdUserById(userId);
        }

        return new JsonResult("success",true);
    }

    public JsonResult update(TestPOJO  user){
        TestPOJO testPOJO=testDao.getUserById(user.getUserId());
        if(testPOJO==null){
            return new JsonResult("没有此userId",false);
        }

        user.setModifiedTime(new Date());
        testDao.updateUser(user);
        return  new JsonResult("success",true);
    }

    public JsonResult detail( String userId,String userCode){
        TestPOJO testPOJO=null;
        if(userId==null){
            testPOJO=testDao.getUserByCode(userCode);
        }else if(userCode==null){
            testPOJO=testDao.getUserById(userId);
        }
        System.out.println(testPOJO);
        return  new JsonResult("success",true,testPOJO);
    }


    public JsonResult list(int pageNum,int pageSize){

//        int pageNum = pageNum == null ? 1 : pageNum;
//        int pageSize = pageSize == null ? 10 : pageSize;

        PageHelper.startPage(pageNum, pageSize);
        List<TestPOJO> list=testDao.getUserList();
        //把list包装成PageInfo对象才能序列化,该插件也默认实现了一个PageInfo
        PageInfo<TestPOJO> pageInfo = new PageInfo<TestPOJO>(list);
        //得到总的数据条数
        Long total=pageInfo.getTotal();
        return  new JsonResult(total,"success",true,list);
    }
}
