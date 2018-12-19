package com.alexc.demobs.controller;

import com.alexc.demobs.entity.Course;
import com.alexc.demobs.entity.Resource.LinkResource;
import com.alexc.demobs.entity.Resource.RepositoryResource;
import com.alexc.demobs.entity.Resource.Resource;
import com.alexc.demobs.entity.User;
import com.alexc.demobs.service.CourseService;
import com.alexc.demobs.service.ResourceService;
import com.alexc.demobs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

@Controller
@RequestMapping("/courses")
public class CoursesController {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ResourceService<Resource> resourceService;

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

    @RequestMapping("/")
    public String getCoursePage(Model model) {

        return "course-temp-page";
    }

    @RequestMapping("/populate")
    @Transactional
    public String populateDatabase(Model theModel) {

        int aCourseId = 2;

        Date deadline = null;
        try {
            deadline = (new SimpleDateFormat("dd-M-yyyy hh:mm:ss")).parse("31-08-2020 10:20:56");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Course aCourse = courseService.findCourseById(aCourseId);

        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("profile.png");

        logger.info("populating tables...");
        RepositoryResource repositoryResource = new RepositoryResource("Git java examples repo",
                new Date(),
                aCourse,"https://github.com/chrishantha/sample-java-programs",
                "Java examples repo...");

        LinkResource linkResource = new LinkResource("link1 - examples website...",
                new Date(),
                aCourse,
                "https://www.cs.utexas.edu/~scottm/cs307/codingSamples.htm",
                "This is a site for java examples");

        resourceService.saveResource(repositoryResource);
        logger.info("saved resource: " + repositoryResource);
        resourceService.saveResource(linkResource);
        logger.info("saved resource: " + linkResource);



        return "courses-list";
    }

}
