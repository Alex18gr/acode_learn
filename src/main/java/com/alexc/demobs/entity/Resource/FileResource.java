package com.alexc.demobs.entity.Resource;

import com.alexc.demobs.entity.Course;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.Date;

@Entity @Data @NoArgsConstructor
@Table(name = "file_resource")
public class FileResource extends Resource {

    @Column(name = "file_name")
    private String name;

    @Column(name = "description")
    private String summary;

    @Lob
    @Column(name = "file_data", columnDefinition = "LONGBLOB")
    private byte[] fileData;

    public FileResource(String name, Date dateCreated, Course course, String name1, String summary, byte[] fileData) {
        super(name, dateCreated, course);
        this.name = name1;
        this.summary = summary;
        this.fileData = fileData;
    }
}
