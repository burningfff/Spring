package com.hisense.sql.controller;

import com.hisense.sql.entity.JsonResult;
import com.hisense.sql.entity.User;
import com.hisense.sql.service.UserServices;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.Iterator;
import java.util.List;
@CrossOrigin//使用这个注解实现对当前controller 的跨域访问
@RestController //使用这个方法代表rest风格的控制器
public class UserController {
    @Autowired
    private UserServices userServices; //注入服务方法；

    @ApiIgnore//使用该注解忽略这个API
    @RequestMapping(value = "/show")  //调用普通服务接口方法；
    public String show(){
        return userServices.show();
    }

    @ApiIgnore//使用该注解忽略这个API
    @RequestMapping(value = "/showDao")  //调用查询数据库接口方法。
    public Object showDao(int age){
        return userServices.showDao(age);
    }

    @SuppressFBWarnings("SBSC_USE_STRINGBUFFER_CONCATENATION")
    @ApiOperation(value="添加用户", notes="添加用户")
    @ApiImplicitParam(name = "user", value = "用户实体", required = true, dataType = "User")
    @RequestMapping(value = "/add", method= RequestMethod.POST)
    public Object add(@Valid @RequestBody User user, BindingResult result){

        System.out.println(user.getUserId()+"+"+user.getUserCode()+"+"+user.getAge()
                +"+"+user.getMail()+"+"+user.getPhone()+"+"+user.getCreateBy()
                +"+"+user.getCreateTime()+"+"+user.getModifiedBy()+"+"+user.getModifiedTime());
        if(result.hasErrors()){

            StringBuffer msg=new StringBuffer();
            List<FieldError> fieldErrors=result.getFieldErrors();
            for (FieldError fieldError:fieldErrors){
                msg.append(fieldError.getDefaultMessage()).append(",");
            }
            System.out.println(msg);
            return new JsonResult(msg.substring(0,msg.length()-1),false);
        }
        System.out.println("tianjia");
        return userServices.add(user);
    }

    @ApiOperation(value="删除用户", notes="根据url的id或code来指定删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = false, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "userCode", value = "用户code", required = false, dataType = "String",paramType = "query")
    })
    @RequestMapping(value = "/delete",method= RequestMethod.POST)
    public Object delete(String userId,String userCode){
        return userServices.delete(userId,userCode);
    }

    @ApiOperation(value="更新用户", notes="更新用户")
    @ApiImplicitParam(name = "user", value = "用户实体", required = true, dataType = "User")
    @RequestMapping(value = "/update",method= RequestMethod.POST)
    public Object update(@RequestBody User  user){
        return userServices.update(user);
    }

    @ApiOperation(value="查找用户", notes="根据url的id或code来指定查找用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = false, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "userCode", value = "用户code", required = false, dataType = "String",paramType = "query")
    })
    @RequestMapping(value = "/detail",method= RequestMethod.GET)
    public Object detail(String userId,String userCode){
        return  userServices.detail(userId,userCode);
    }

    @ApiOperation(value="查找用户列表", notes="查找用户列表")
    @RequestMapping(value = "/list",method= RequestMethod.GET)
    public Object list(){
        int pageNum=1;
        int pageSize=10;
        return userServices.list(pageNum,pageSize);
    }
    @ApiOperation(value="查找用户列表", notes="指定查询关键字和分页过着查找用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "select", value = "用户查询关键字代号", required = false, dataType = "String"),
            @ApiImplicitParam(name = "parameter", value = "用户查询关键字", required = false, dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "分页数量", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每一页的大小", required = false, dataType = "Integer")
    })
    @RequestMapping(value = "/getinfo",method= RequestMethod.GET)
    public JsonResult getInfos(@RequestParam(value="select") String select,@RequestParam(value="parameter") String parameter,
                                               @RequestParam(value="pageNum")int pageNum, @RequestParam(value="pageSize")int pageSize) {

        return userServices.getInfos(select,parameter,pageNum,pageSize);
    }

}
