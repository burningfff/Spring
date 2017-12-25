package com.hisense.mysqlfeign.controller;

import com.hisense.mysqlfeign.entity.User;
import com.hisense.mysqlfeign.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin//使用这个注解实现对当前controller 的跨域访问
@RestController //使用这个方法代表rest风格的控制器
public class UserController {
    @Autowired
    private UserServices userServices; //注入服务方法；

    @RequestMapping(value = "/show")  //调用普通服务接口方法；
    public String show(){
        return userServices.show();
    }
/*

    @ApiIgnore//使用该注解忽略这个API
    @RequestMapping(value = "/showDao")  //调用查询数据库接口方法。
    public Object showDao(int age){
        return userServices.showDao(age);
    }
*/

    @RequestMapping(value = "/add", method= RequestMethod.POST)
    public void add(@Valid @RequestBody User user, BindingResult result){
        userServices.add(user);
    }


    @RequestMapping(value = "/delete",method= RequestMethod.POST)
    public void delete(String userId,String userCode){
        userServices.delete(userId,userCode);
    }

    @RequestMapping(value = "/update",method= RequestMethod.POST)
    public void update(@RequestBody User  user){
         userServices.update(user);
    }

    @RequestMapping(value = "/detail",method= RequestMethod.GET)
    public void detail(String userId,String userCode){
        userServices.detail(userId,userCode);
    }

    @RequestMapping(value = "/list",method= RequestMethod.GET)
    public void list(){
        userServices.list();
    }

    @RequestMapping(value = "/getinfo",method= RequestMethod.GET)
    public void getInfos(@RequestParam(value="select") String select,@RequestParam(value="parameter") String parameter,
                                               @RequestParam(value="pageNum")int pageNum, @RequestParam(value="pageSize")int pageSize) {

        userServices.getInfos(select,parameter,pageNum,pageSize);
    }

}
