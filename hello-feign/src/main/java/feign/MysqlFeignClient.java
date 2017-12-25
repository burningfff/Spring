package feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

//通过@ FeignClient（“服务名”），来指定调用哪个服务

@FeignClient(value = "mysql-service",fallback = MysqlClientHystrix.class)
public interface MysqlFeignClient {

    @RequestMapping(value="/add",method = RequestMethod.PUT)
    Object add(@RequestBody Object user);

    @RequestMapping(value="/delete",method = RequestMethod.PUT)
    Object delete(@RequestParam(value="userId") String userId,@RequestParam(value="userCode") String userCode);

    @RequestMapping(value="/update",method = RequestMethod.PUT)
    Object update(@RequestBody Object user);

    @GetMapping(value="/detail")
    Object detail(@RequestParam(value="userId") String userId,@RequestParam(value="userCode") String userCode);

    @GetMapping(value="/list")  //@GetMapping是一个组合注解，是@RequestMapping(method = RequestMethod.GET)的缩写
    Object list();

    @GetMapping(value="/getInfos")
    Object getInfos(@RequestParam(value="select") String select,@RequestParam(value="parameter") String parameter,
                    @RequestParam(value="pageNum")int pageNum, @RequestParam(value="pageSize")int pageSize);

}
