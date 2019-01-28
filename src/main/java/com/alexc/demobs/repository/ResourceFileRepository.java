package com.alexc.demobs.repository;

import com.alexc.demobs.entity.Resource.FileResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceFileRepository extends JpaRepository<FileResource, String> {

}