package com.alexc.demobs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoAppController {

    private String appMode;

    @Autowired
    public DemoAppController(Environment environment){
        appMode = environment.getProperty("app-mode");
    }

    @GetMapping("/")
    public String HomeMapping() {
        return "home";
    }

    @GetMapping("/user-profile")
    public String getUserProfilePage() {
        return "profile";
    }

}
