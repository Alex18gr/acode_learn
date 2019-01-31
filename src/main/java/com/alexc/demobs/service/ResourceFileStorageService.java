package com.alexc.demobs.service;

import com.alexc.demobs.entity.Course;
import com.alexc.demobs.entity.Resource.FileResource;
import org.springframework.web.multipart.MultipartFile;

public interface ResourceFileStorageService {

    FileResource storeResourceFile(FileResource fileResource);

    FileResource findById(int id);
}
