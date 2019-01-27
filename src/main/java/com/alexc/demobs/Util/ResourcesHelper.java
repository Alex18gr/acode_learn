package com.alexc.demobs.Util;

import com.alexc.demobs.entity.Resource.FileResource;
import com.alexc.demobs.entity.Resource.LinkResource;
import com.alexc.demobs.entity.Resource.RepositoryResource;
import com.alexc.demobs.entity.Resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
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
}
