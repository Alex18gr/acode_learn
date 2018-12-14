package com.alexc.demobs.service;

import com.alexc.demobs.dao.CourseDao;
import com.alexc.demobs.dao.CourseDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDaoImpl courseDao;





}
