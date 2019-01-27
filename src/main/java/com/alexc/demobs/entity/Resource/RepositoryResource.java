package com.alexc.demobs.entity.Resource;

import com.alexc.demobs.entity.Course;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity @Data @NoArgsConstructor
@Table(name = "repo_resource")
public class RepositoryResource extends Resource {

    @Column(name = "repo_url")
    private String repoUrl;

    @Column(name = "repo_name")
    private String repoName;

    @Column(name = "repo_user_repo")
    private String repoNameRepo;

    public RepositoryResource(String name, Date dateCreated, Course course, String repoUrl, String repoName, String repoNameRepo) {
        super(name, dateCreated, course);
        this.repoUrl = repoUrl;
        this.repoName = repoName;
        this.repoNameRepo = repoNameRepo;
    }
}
