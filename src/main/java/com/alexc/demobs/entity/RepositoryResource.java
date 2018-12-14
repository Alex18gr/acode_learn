package com.alexc.demobs.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Data
@Table(name = "repo_resource")
public class RepositoryResource extends Resource{

    private String repoUrl;

    private String repoName;

}
