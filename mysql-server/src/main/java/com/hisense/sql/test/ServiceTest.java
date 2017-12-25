package com.hisense.sql.test;

import com.hisense.sql.entity.JSONUtils;
import com.hisense.sql.entity.JsonResult;
import com.hisense.sql.MysqlApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@RunWith(SpringRunner.class)// SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = MysqlApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServiceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public  void add() throws Exception{
        String url="http://localhost:2230/add";
        String json = "{\n" +
                "    \"userCode\": \"jsahdjas\",\n" +
                "    \"age\": 32,\n" +
                "    \"mail\": \"221523121@163.com\",\n" +
                "    \"phone\": \"17863130918\"\n" +
                "}";
        JsonResult result = postJson(url, json);
        System.out.println("--------新增--------");
        System.out.println(JSONUtils.obj2json(result));
    }

    @Test
    public void update() throws Exception{
        String url="http://127.0.0.1:2230/update";
        String json= "{\n" +
                "    \"userId\": \"0\",\n" +
                "    \"userCode\": \"jsahdjas\",\n" +
                "    \"age\": 22,\n" +
                "    \"mail\": \"2215421@163.com\",\n" +
                "    \"phone\": \"17863135518\"\n" +
                "}";
        JsonResult result=postJson(url,json);
        System.out.println("----更新-----");
        System.out.println(JSONUtils.obj2json(result));
    }

    //post发送参数为Json格式的数据
    private JsonResult postJson(String url,String json){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(json, headers);
        return this.restTemplate.postForObject(url, entity, JsonResult.class);
    }
    //post发送参数为普通参数格式的数据
    public JsonResult postParam(String url, Object params) {
        return this.restTemplate.postForObject(url, params, JsonResult.class);
    }
    public JsonResult getJsonByGet(String url, Map map) {
        return this.restTemplate.getForObject(url, JsonResult.class, map);
    }


}
