package com.yinggu.plane.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName IndexController
 * @Description TODO
 * @Author wangyichen
 * Date 2022/4/16 14:49
 * Version 1.0
 */
@Controller
@RequestMapping("/flight")
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
