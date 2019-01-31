package com.alexc.demobs.dao;

import com.alexc.demobs.entity.Resource.FileResource;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public class FileResourceUploadDAOImpl implements FileResourceUploadDAO {


    private static final Logger logger
            = LoggerFactory.getLogger(FileResourceUploadDAOImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void save(FileResource fileResource) {
        logger.info(sessionFactory.getCurrentSession().save(fileResource).getClass().getName());
    }

    @Override
    @Transactional
    public FileResource findById(int id) {
        return sessionFactory.getCurrentSession().get(FileResource.class, id);
    }
}
