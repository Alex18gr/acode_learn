package com.alexc.demobs.dao;

import com.alexc.demobs.entity.Course;
import com.alexc.demobs.entity.Resource;

import java.util.List;

public interface ResourceDao<T extends Resource> {


        void saveResource(T resource);

        T getResourceById(int id, Class<T> tClass);

        void deleteResource(int id, Class<T> tClass);
}
