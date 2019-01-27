package com.alexc.demobs.service;

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

import java.io.IOException;

@Service
public class ResourceFileStorageService {

    private static final Logger logger = LoggerFactory.getLogger(ResourceFileStorageService.class);

    @Autowired
    private ResourceFileRepository resourceFileRepository;

    public FileResource storeResourceFile(MultipartFile file, Course course) {

        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {

            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            FileResource fileResource = new FileResource();
            fileResource.setFileType(file.getContentType());
            fileResource.setFileName(fileName);
            fileResource.setFileData(file.getBytes());
            fileResource.setCourse(course);
            logger.info("Saving file: " + fileResource + " into course: " + course);
            return resourceFileRepository.save(fileResource);
        } catch (IOException e) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", e);
        }
    }

    public FileResource getFile(String fileId) {
        return resourceFileRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }
}
