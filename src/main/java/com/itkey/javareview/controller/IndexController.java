package com.itkey.javareview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页
 * 日期：2021-01-23
 *
 */
@Controller
public class IndexController {
    @RequestMapping(value = "/")
    public String getIndex(){
        return "redirect:/index.html"; //返回index页面
    }

}
