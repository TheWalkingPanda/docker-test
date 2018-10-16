package com.bing.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bing on 2018/10/15.
 */
@RestController
@RequestMapping("/胖超")
public class TestController {
    @RequestMapping("/帅么")
    public String test() {
        return "胖超你真帅";
    }
}
