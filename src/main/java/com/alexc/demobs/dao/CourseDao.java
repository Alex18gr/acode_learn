package com.alexc.demobs.dao;

import com.alexc.demobs.entity.Course;
import com.alexc.demobs.entity.Resource;

import java.util.List;

public interface CourseDao {

    Course findCourseById(int courseId);

    void saveCourse(Course mCourse);

    //List<Resource> findResourcesByCourse(Course course);

}
