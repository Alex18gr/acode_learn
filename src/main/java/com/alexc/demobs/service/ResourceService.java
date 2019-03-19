package com.alexc.demobs.service;

import com.alexc.demobs.entity.Resource.Resource;

public interface ResourceService<T extends Resource> {

    void saveResource(T resource);

    T getResourceById(int id, Class<T> tClass);

    void deleteResource(int id, Class<T> tClass);

}
