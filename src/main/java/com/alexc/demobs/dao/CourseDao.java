package com.alexc.demobs.dao;

import com.alexc.demobs.entity.Course;

public interface CourseDao {

    Course findCourseById(int courseId);

    void saveCourse(Course mCourse);

    //List<Resource> findResourcesByCourse(Course course);

}
