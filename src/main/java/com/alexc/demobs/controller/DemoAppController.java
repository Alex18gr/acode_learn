package com.alexc.demobs.controller;

import com.alexc.demobs.entity.User;
import com.alexc.demobs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoAppController {


    @Autowired
    private UserService userService;

    private String appMode;

    @Autowired
    public DemoAppController(Environment environment){
        appMode = environment.getProperty("app-mode");
    }

    @GetMapping("/")
    public String HomeMapping() {
        return "home";
    }

    @RequestMapping("/not-authorized")
    public String getNotAuthorized() {
        return "not-authorized";
    }

    @GetMapping("/user-profile")
    public String getUserProfilePage(Model theModel) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        User theUser = userService.findByUserName(name);
        theModel.addAttribute("firstName", theUser.getFirstName());
        theModel.addAttribute("email", theUser.getEmail());

        return "profile";
    }

}
