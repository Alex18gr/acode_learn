package com.alexc.demobs.controller;

import com.alexc.demobs.entity.User;
import com.alexc.demobs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.logging.Logger;

@Controller
@RequestMapping("/courses")
public class CoursesController {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    private UserService userService;

    @RequestMapping("/course-list")
    @Transactional
    public String getCoursesListPage(Model theModel, HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        User theUser = userService.findByUserName(name);
        theModel.addAttribute("firstName", theUser.getFirstName());
        theModel.addAttribute("email", theUser.getEmail());
        logger.info("getting the courses....");

        theModel.addAttribute("courses", theUser.getCoursesEnrolled());
        logger.info(theUser.getCoursesEnrolled().toString());
        logger.info("courses in the model");
        if(request.isUserInRole("ADMIN")) {
            logger.info("user in role ADMIN");
        } else {
            logger.info("user is not admin");
        }

        return "courses-list";
    }

}
