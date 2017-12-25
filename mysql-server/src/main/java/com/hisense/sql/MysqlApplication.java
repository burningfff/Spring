package com.hisense.sql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication()
@EnableDiscoveryClient
//@ComponentScan(basePackages={"com.hisense.sql.test"})
public class MysqlApplication {
    public static void main(String[] args){
        SpringApplication.run(MysqlApplication.class,args);
    }
}
