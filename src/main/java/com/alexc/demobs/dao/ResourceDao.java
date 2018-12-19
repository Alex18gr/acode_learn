package com.alexc.demobs.dao;

import com.alexc.demobs.entity.Resource.Resource;

public interface ResourceDao<T extends Resource> {


        void saveResource(T resource);

        T getResourceById(int id, Class<T> tClass);

        void deleteResource(int id, Class<T> tClass);
}
