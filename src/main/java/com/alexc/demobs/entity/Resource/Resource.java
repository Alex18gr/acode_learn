package com.alexc.demobs.entity.Resource;

import com.alexc.demobs.entity.Course;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity @Data @NoArgsConstructor
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

    public Resource(String name, Date dateCreated, Course course) {
        this.name = name;
        this.dateCreated = dateCreated;
        this.course = course;
    }

    @PostUpdate
    private void updateDateOnUpdate() {
        this.dateCreated = new Date();
    }

    public String getClassName() {
        return this.getClass().getName();
    }

}
