package com.alexc.demobs.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity @Data
@Table(name = "resource")
@Inheritance(strategy = InheritanceType.JOINED)
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected int resourceId;

    @Column(name = "name")
    protected String name;

    @Column(name = "datetime_created")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date dateCreated = new Date();

    @ManyToOne(
            cascade = {CascadeType.PERSIST,CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH}
    )
    @JoinColumn(name = "course_id", nullable = false)
    protected Course course;

    private void doSth() {

    }

    @PostUpdate
    private void updateDateOnUpdate() {
        this.dateCreated = new Date();
    }


}
