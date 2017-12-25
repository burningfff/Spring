package com.hisense.mysqlfeign.test;

import com.hisense.mysqlfeign.MysqlFeignApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringJUnit4ClassRunner.class)// SpringJUnit支持，由此引入Spring-Test框架支持！
@RunWith(SpringRunner.class)// SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = MysqlFeignApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)//指定我们SpringBoot工程的Application启动类
//@WebAppConfiguration
public class BaseTest {
}
