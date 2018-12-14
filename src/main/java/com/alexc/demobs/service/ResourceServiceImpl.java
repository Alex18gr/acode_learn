package com.alexc.demobs.service;

import com.alexc.demobs.dao.ResourceDao;
import com.alexc.demobs.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;

public class ResourceServiceImpl<T extends Resource> implements ResourceService<T> {

    @Autowired
    private ResourceDao<T> resourceDao;


    @Override
    public void saveResource(T resource) {
        resourceDao.saveResource(resource);
    }

    @Override
    public T getResourceById(int id, Class<T> tClass) {
        return resourceDao.getResourceById(id, tClass);
    }

    @Override
    public void deleteResource(int id, Class<T> tClass) {
        resourceDao.deleteResource(id, tClass);
    }
}
