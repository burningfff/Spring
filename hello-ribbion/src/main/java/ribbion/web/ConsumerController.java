package ribbion.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;//通过注入ioc容器的restTemplate来消费hello-service服务的“/hello”接口

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
//      this.loadBalancerClient.choose("hello");//随机访问策略
//        System.out.println("111-->"+instance.getHost()+":"+instance.getServiceId()+":"+instance.getPort());
        return restTemplate.getForEntity("http://hello-service/hello", String.class).getBody();
        //return restTemplate.getForObject("http://hello-service/hello",String.class);
    }

}