package com.alexc.demobs.Util;

import com.alexc.demobs.entity.Resource.FileResource;
import com.alexc.demobs.entity.Resource.LinkResource;
import com.alexc.demobs.entity.Resource.RepositoryResource;
import com.alexc.demobs.entity.Resource.Resource;
import com.alexc.demobs.exception.FileStorageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResourcesHelper {

    public final static String RES_FILE = "FileResource";
    public final static String RES_LINK = "LinkResource";
    public final static String RES_REPOSITORY = "RepositoryResource";

    private static final Logger logger = LoggerFactory.getLogger(ResourcesHelper.class);

    List<LinkResource> linkResources = new ArrayList<>();
    List<RepositoryResource> repositoryResources = new ArrayList<>();
    List<FileResource> fileResources = new ArrayList<>();

    public ResourcesHelper(List<Resource> resources) {


        // Retrieve the resources and split into collections
        for (Resource resource : resources) {
            if (resource.getClass().getSimpleName().toString().equals(RES_FILE)) {
                fileResources.add((FileResource) resource);
            } else if (resource.getClass().getSimpleName().toString().equals(RES_LINK)) {
                linkResources.add((LinkResource) resource);
            } else if (resource.getClass().getSimpleName().toString().equals(RES_REPOSITORY)) {
                repositoryResources.add((RepositoryResource) resource);
            }
        }
    }

    public void printResults() {
        logger.info("printing Resources...");
        for (Resource r : fileResources) logger.info(r + "");
        logger.info("Repository Resources");
        logger.info("Link Resources");
        for (Resource r : linkResources) logger.info(r + "");
        logger.info("File Resources");
        for (Resource r : repositoryResources) logger.info(r + "");
    }

    public List<LinkResource> getLinkResources() {
        return linkResources;
    }

    public List<RepositoryResource> getRepositoryResources() {
        return repositoryResources;
    }

    public List<FileResource> getFileResources() {
        return fileResources;
    }

    public static FileResource getFileResource(MultipartFile file) {
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
            return fileResource;
        } catch (IOException e) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", e);
        }
    }
}
