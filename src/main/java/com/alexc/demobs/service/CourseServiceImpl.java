package com.alexc.demobs.service;

import com.alexc.demobs.dao.CourseDaoImpl;
import com.alexc.demobs.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDaoImpl courseDao;


    @Override
    public Course findCourseById(int courseId) {
        return courseDao.findCourseById(courseId);
    }

    @Override
    public void saveCourse(Course mCourse) {
        courseDao.saveCourse(mCourse);
    }
}
