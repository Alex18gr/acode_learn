package com.alexc.demobs.dao;

import com.alexc.demobs.entity.Resource.FileResource;

public interface FileResourceUploadDAO {

    void save(FileResource fileResource);

    FileResource findById(int id);

}
