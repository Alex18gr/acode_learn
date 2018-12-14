package com.alexc.demobs.dao;

import com.alexc.demobs.entity.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Repository;

import java.util.logging.Logger;

@Repository
public class CourseDaoImpl implements CourseDao {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Course findCourseById(int courseId) {

        Session currentSession = sessionFactory.getCurrentSession();

        Course theCourse = currentSession.get(Course.class, courseId);
        if(theCourse != null) {
            logger.info("Retrieved course with id=" + courseId);
        }

        return theCourse;
    }

    @Override
    public void saveCourse(Course mCourse) {
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.saveOrUpdate(mCourse);
        logger.info("Saved or Updated course: " + mCourse);
    }
}
