package com.learn.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Hearts
 * @date 2019/3/22
 * @desc
 */
@Controller
public class SystemController {

    @RequestMapping("/login")
    public String login(){
        System.out.println("hello");
        return "login";
    }
}
