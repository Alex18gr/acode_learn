package com.alexc.demobs.service;

import com.alexc.demobs.dao.FileResourceUploadDAO;
import com.alexc.demobs.entity.Course;
import com.alexc.demobs.entity.Resource.FileResource;
import com.alexc.demobs.exception.FileStorageException;
import com.alexc.demobs.exception.MyFileNotFoundException;
import com.alexc.demobs.repository.ResourceFileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ResourceFileStorageServiceImpl implements ResourceFileStorageService {

    private static final Logger logger = LoggerFactory.getLogger(ResourceFileStorageServiceImpl.class);

    @Autowired
    private ResourceFileRepository resourceFileRepository;

    @Autowired
    private FileResourceUploadDAO fileResourceUploadDAO;

    @Override
    public FileResource storeResourceFile(FileResource fileResource) {
        fileResourceUploadDAO.save(fileResource);
        return fileResource;
    }

    @Override
    public FileResource findById(int id) {
        return fileResourceUploadDAO.findById(id);
    }


//    public FileResource getFile(String fileId) {
//        return resourceFileRepository.findById(fileId)
//                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
//    }
}
