package com.alexc.demobs.service;

import com.alexc.demobs.entity.Course;

public interface CourseService {

    Course findCourseById(int courseId);

    void saveCourse(Course mCourse);

}
