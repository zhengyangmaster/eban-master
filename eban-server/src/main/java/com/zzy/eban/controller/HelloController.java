package com.zzy.eban.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhuZhengYang
 * @description 测试专用
 * @since 2022/2/23
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "mmp";
    }

    @GetMapping("/employee/basic/hello")
    public String hello2(){
        return "/employee/basic/hello";

    }

    @GetMapping("/employee/advanced/hello")
    public String hello3(){
        return "/employee/advanced/hello";

    }

}
