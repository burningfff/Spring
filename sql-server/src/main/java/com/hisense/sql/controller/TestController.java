package com.hisense.sql.controller;

import com.hisense.sql.domain.JsonResult;
import com.hisense.sql.domain.TestPOJO;
import com.hisense.sql.service.TestServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController //使用这个方法代表rest风格的控制器
public class TestController {

    @Autowired
    private TestServices testServices; //注入服务方法；

    @RequestMapping(value = "/show")  //调用普通服务接口方法；
    public String show(){
        return testServices.show();
    }

    @RequestMapping(value = "/showDao")  //调用查询数据库接口方法。
    public Object showDao(int age){
        return testServices.showDao(age);
    }

    @RequestMapping(value = "/add", method= RequestMethod.POST)
    public Object add(@Valid @RequestBody TestPOJO user, BindingResult result){
        if(result.hasErrors()){
            String msg="";
            List<FieldError> fieldErrors=result.getFieldErrors();
            for (FieldError fieldError:fieldErrors){
                msg+=fieldError.getDefaultMessage()+",";
            }
            return new JsonResult(msg.substring(0,msg.length()-1),false);
        }
        return testServices.add(user);
    }

    @RequestMapping(value = "/delete",method= RequestMethod.POST)
    public Object delete(String userId,String userCode){
        return testServices.delete(userId,userCode);
    }

    @RequestMapping(value = "/update",method= RequestMethod.POST)
    public Object update(@RequestBody TestPOJO  user){
        return testServices.update(user);
    }

    @RequestMapping(value = "/detail",method= RequestMethod.GET)
    public Object detail(String userId,String userCode){
        return  testServices.detail(userId,userCode);
    }

    @RequestMapping(value = "/list",method= RequestMethod.GET)
    public Object list(){
        int pageNum=1;
        int pageSize=5;
        return testServices.list(pageNum,pageSize);
    }

}