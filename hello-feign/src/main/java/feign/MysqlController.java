package feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MysqlController {
    @Autowired
    MysqlFeignClient mysqlFeignClient;

    @RequestMapping(value="/add",method = RequestMethod.PUT)
    public Object add(@RequestBody Object user){
        return mysqlFeignClient.add(user);
    };

//    @RequestMapping(value="/delete",method = RequestMethod.PUT)
//    Object delete(String userId,String userCode){
//        return mysqlFeignClient.delete(userId,userCode);
//    };

    @RequestMapping(value="/update",method = RequestMethod.PUT)
    public Object update(@RequestBody Object user){
        return mysqlFeignClient.update(user);
    };

    @GetMapping(value="/detail")
    public Object detail(String userId,String userCode){
        return mysqlFeignClient.detail(userId,userCode);
    };

    @GetMapping(value="/list")  //@GetMapping是一个组合注解，是@RequestMapping(method = RequestMethod.GET)的缩写
    public Object list(){
        return  mysqlFeignClient.list();
    };

    @GetMapping(value="/getInfos")
    public Object getInfos(@RequestParam(value="select") String select,@RequestParam(value="parameter") String parameter,
                    @RequestParam(value="pageNum")int pageNum, @RequestParam(value="pageSize")int pageSize){
        return  mysqlFeignClient.getInfos(select,parameter,pageNum,pageSize);
    };
}
