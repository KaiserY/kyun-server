package com.kaisery.fs.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping("/helloworld")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    String helloWorld() {
        return "Hello World!";
    }
}
