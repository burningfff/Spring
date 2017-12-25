package gateway;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by tommy on 2017/10/18.
 */
@EnableZuulProxy
@SpringBootApplication
public class GateWayApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder(GateWayApp.class).web(true).run(args);
    }

}
