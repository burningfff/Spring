package com.hisense.hello.web;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tommy on 2017/10/18.
 */
@RestController
public class HelloResource {

    @RequestMapping("/hello")
    public Object hello() {
        return "GYH";
    }
}
