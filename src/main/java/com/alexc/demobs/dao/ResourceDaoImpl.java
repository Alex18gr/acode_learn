package com.alexc.demobs.dao;


import com.alexc.demobs.entity.Resource.Resource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.logging.Logger;

@Repository
public class ResourceDaoImpl<T extends Resource> implements ResourceDao<T> {

    private T t;

    Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveResource(T resource) {

        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(resource);
        logger.info("saved " + resource.getClass().getName() + " resource: " + resource);

    }

    @Override
    @Transactional
    public T getResourceById(int id, Class<T> tClass) {
        Session session = sessionFactory.getCurrentSession();

        T resource = session.get(tClass, id);

        return null;
    }

    @Override
    @Transactional
    public void deleteResource(int id, Class<T> tClass) {

        Session session = sessionFactory.getCurrentSession();

        T resource = getResourceById(id, tClass);
        session.delete(resource);
        logger.info("Deleted " + resource.getClass().getName() + " resource: " + resource);

    }
}
