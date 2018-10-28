package com.alexc.demobs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping(value = {"/", "admin-console"})
    public String getAdminConsole(Model theModel, HttpServletRequest request) {

        if(!request.isUserInRole("ADMIN")) return "redirect:/not-authorized";

        return "admin/admin-console";

    }

}
