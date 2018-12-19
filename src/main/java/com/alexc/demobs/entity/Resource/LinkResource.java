package com.alexc.demobs.entity.Resource;

import com.alexc.demobs.entity.Course;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity @Data
@Table(name = "link_resource")
public class LinkResource extends Resource {

    @Column(name = "link")
    private String link;

    @Column(name = "description")
    private String description;

    public LinkResource(String name, Date dateCreated, Course course, String link, String description) {
        super(name, dateCreated, course);
        this.link = link;
        this.description = description;
    }
}
