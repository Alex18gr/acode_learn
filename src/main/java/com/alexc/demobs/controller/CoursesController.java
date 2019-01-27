package com.alexc.demobs.controller;

import com.alexc.demobs.Util.ResourcesHelper;
import com.alexc.demobs.config.SecurityConfig;
import com.alexc.demobs.entity.Course;
import com.alexc.demobs.entity.Resource.LinkResource;
import com.alexc.demobs.entity.Resource.RepositoryResource;
import com.alexc.demobs.entity.Resource.Resource;
import com.alexc.demobs.entity.User;
import com.alexc.demobs.service.CourseService;
import com.alexc.demobs.service.ResourceFileStorageService;
import com.alexc.demobs.service.ResourceService;
import com.alexc.demobs.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.LazyContextVariable;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    @Autowired
    private ResourceFileStorageService resourceFileStorageService;

    @RequestMapping("/course-list")
    @Transactional
    public String getCoursesListPage(Model theModel, HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        User theUser = userService.findByUserName(name);
        theModel.addAttribute("firstName", theUser.getFirstName());
        theModel.addAttribute("email", theUser.getEmail());
        logger.info("getting the courses....");
        if (request.isUserInRole(SecurityConfig.ROLE_STUDENT)) {
            theModel.addAttribute("courses", theUser.getCoursesEnrolled());
            logger.info(theUser.getCoursesEnrolled().toString());
        } else if (request.isUserInRole(SecurityConfig.ROLE_TEACHER)) {
            theModel.addAttribute("courses", theUser.getCoursesOwned());
            logger.info(theUser.getCoursesOwned().toString());
        }

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
                "Java examples repo...",
                "chrishantha/sample-java-programs");

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

    @RequestMapping(value = "/{courseId}", method = RequestMethod.GET)
    @Transactional
    public String getCourseHomePage(@PathVariable int courseId,
                                           Model theModel,
                                           HttpServletRequest request
    ) {

        // obtaining the current user
        User theUser = getCurrentUser();

        // obtain the course from the url
        Course course = courseService.findCourseById(courseId);

        // check if the student is enrolled to the course
        if(course.getStudentsEnrolled().contains(theUser)
                || course.getInstructors().contains(theUser)) {
            if (course.getInstructors().contains(theUser)) {
                theModel.addAttribute("isInstructor", true);
            } else {
                theModel.addAttribute("isInstructor", false);
            }
            course.getInstructors().size();
            theModel.addAttribute("course", course);
            logger.info("Directing to course home page...");
            return "course/course-home";
        }
        return "not-authorized";
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        return userService.findByUserName(userName);
    }

    @RequestMapping(value = "/{courseId}/information", method = RequestMethod.GET)
    @Transactional
    public String getCourseInformationPage(@PathVariable int courseId,
                                           Model theModel,
                                           HttpServletRequest request
    ) {
        // obtaining the current user

        User theUser = getCurrentUser();

        // obtain the course from the url
        Course course = courseService.findCourseById(courseId);

        // check if the student is enrolled to the course
        if(course.getStudentsEnrolled().contains(theUser)
                || course.getInstructors().contains(theUser)) {
            if (course.getInstructors().contains(theUser)) {
                theModel.addAttribute("isInstructor", true);
            } else {
                theModel.addAttribute("isInstructor", false);
            }
            course.getInstructors().size();
            theModel.addAttribute("course", course);
            logger.info("Directing to course info page...");
            return "course/course-info";
        }
        return "not-authorized";
    }

    @RequestMapping(value = "/{courseId}/resources/files", method = RequestMethod.GET)
    @Transactional
    public String getCourseResourcesFilesPage(@PathVariable int courseId,
                                           Model theModel,
                                           HttpServletRequest request
    ) {
        // obtaining the current user
        User theUser = getCurrentUser();

        // obtain the course from the url
        Course course = courseService.findCourseById(courseId);

        // check if the student is enrolled to the course
        if(course.getStudentsEnrolled().contains(theUser)
                || course.getInstructors().contains(theUser)) {
            if (course.getInstructors().contains(theUser)) {
                theModel.addAttribute("isInstructor", true);
            } else {
                theModel.addAttribute("isInstructor", false);
            }
            course.getCourseResources().size();
            ResourcesHelper helper = new ResourcesHelper(course.getCourseResources());
            theModel.addAttribute("fileResourcesList", helper.getFileResources());
            theModel.addAttribute("course", course);
            logger.info("Directing to course info page...");
            return "course/course-resources-files";
        }
        return "not-authorized";
    }

    @RequestMapping(value = "/{courseId}/resources", method = RequestMethod.GET)
    @Transactional
    public String getCourseResourcesPage(@PathVariable int courseId,
                                           Model theModel,
                                           HttpServletRequest request
    ) {
        // obtaining the current user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        User theUser = userService.findByUserName(userName);

        // obtain the course from the url
        Course course = courseService.findCourseById(courseId);

        // check if the student is enrolled to the course
        if(course.getStudentsEnrolled().contains(theUser)
                || course.getInstructors().contains(theUser)) {
            if (course.getInstructors().contains(theUser)) {
                theModel.addAttribute("isInstructor", true);
            } else {
                theModel.addAttribute("isInstructor", false);
            }
            course.getInstructors().size();
            course.getCourseResources().size();
            ResourcesHelper helper = new ResourcesHelper(course.getCourseResources());
            helper.printResults();
            theModel.addAttribute("fileResourcesList", helper.getFileResources());
            theModel.addAttribute("linkResourcesList", helper.getLinkResources());
            theModel.addAttribute("repositoryResourcesList", helper.getRepositoryResources());
            theModel.addAttribute("course", course);
            logger.info("Directing to course resources page...");
            return "course/course-resources";
        }
        return "not-authorized";
    }

    @RequestMapping(value = "/{courseId}/resources/repositories", method = RequestMethod.GET)
    @Transactional
    public String getCourseResourcesRepositoriesPage(@PathVariable int courseId,
                                         Model theModel,
                                         HttpServletRequest request
    ) {
        // obtaining the current user
        User theUser = getCurrentUser();

        // obtain the course from the url
        Course course = courseService.findCourseById(courseId);

        // check if the student is enrolled to the course
        if(course.getStudentsEnrolled().contains(theUser)
                || course.getInstructors().contains(theUser)) {
            if (course.getInstructors().contains(theUser)) {
                theModel.addAttribute("isInstructor", true);
            } else {
                theModel.addAttribute("isInstructor", false);
            }
            course.getCourseResources().size();
            ResourcesHelper helper = new ResourcesHelper(course.getCourseResources());
            //helper.printResults();
            theModel.addAttribute("repositoryResourcesList", helper.getRepositoryResources());
            theModel.addAttribute("course", course);
            logger.info("Directing to course resources repositories page...");
            return "course/course-resources-repositories";
        }
        return "not-authorized";
    }



    

}
