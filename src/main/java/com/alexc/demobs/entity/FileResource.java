package com.alexc.demobs.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.Date;

@Entity @Data
@Table(name = "file_resource")
public class FileResource extends Resource {

    @Column(name = "file_name")
    private String name;


    private Date dateCreated = new Date();

    private String summary;

    @Lob
    @Column(name = "file_data", columnDefinition = "LONGBLOB")
    private byte[] fileData;


}
